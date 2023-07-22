package com.sophix.patch.activities

import android.content.ClipData
import android.content.ClipboardManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.TextUtils
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.EditText
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton
import com.nightonke.boommenu.BoomMenuButton
import com.sophix.patch.R
import com.sophix.patch.R2
import com.sophix.patch.adapter.GridViewAdapter
import com.sophix.patch.adapter.MyPageAdapter
import com.sophix.patch.utils.constant.Constants
import com.sophix.patch.utils.helper.ExpressionHelper
import me.grantland.widget.AutofitHelper
import java.lang.reflect.Method
import java.util.Arrays
import java.util.regex.Pattern

/**
 * 计算器主界面
 */
class MainActivity : AppCompatActivity() {
    @JvmField
    @BindView(R2.id.tv_clear_all)
    var mClearAllTextView: TextView? = null

    @JvmField
    @BindView(R2.id.tv_delete)
    var mDeleteTextView: TextView? = null

    @JvmField
    @BindView(R2.id.tv_copy)
    var mCopyTextView: TextView? = null

    @JvmField
    @BindView(R2.id.grid_view_numeric_menu)
    var mNumericGridViewMenu: GridView? = null

    @JvmField
    @BindView(R2.id.tv_add)
    var mAddTextView: TextView? = null

    @JvmField
    @BindView(R2.id.tv_sub)
    var mSubTextView: TextView? = null

    @JvmField
    @BindView(R2.id.tv_mul)
    var mMulTextView: TextView? = null

    @JvmField
    @BindView(R2.id.tv_div)
    var mDivTextView: TextView? = null

    @JvmField
    @BindView(R2.id.tv_percent)
    var mPercentTextView: TextView? = null

    @JvmField
    @BindView(R2.id.tv_power)
    var mPowerTextView: TextView? = null

    @JvmField
    @BindView(R2.id.tv_degree)
    var mDegreeTextView: TextView? = null

    @JvmField
    @BindView(R2.id.tv_factorial)
    var mFactorialTextView: TextView? = null

    @JvmField
    @BindView(R2.id.tv_square_root)
    var mSquareRootTextView: TextView? = null

    @JvmField
    @BindView(R2.id.tv_infinity)
    var mInfinityTextView: TextView? = null

    @JvmField
    @BindView(R2.id.tv_imaginary_num_unit)
    var mImaginaryNumUnitTextView: TextView? = null

    @JvmField
    @BindView(R2.id.tv_double_brace)
    var mDoubleBraceTextView: TextView? = null

    @JvmField
    @BindView(R2.id.tv_comma)
    var mCommaTextView: TextView? = null

    @JvmField
    @BindView(R2.id.tv_x_parameter)
    var mXParameterTextView: TextView? = null

    @JvmField
    @BindView(R2.id.iv_drawer_left_arrow)
    var drawerRightImageView: ImageView? = null

    @JvmField
    @BindView(R2.id.drawer)
    var mDrawer: DrawerLayout? = null

    @JvmField
    @BindView(R2.id.tab_bar)
    var mTabBar: TabLayout? = null

    @JvmField
    @BindView(R2.id.view_pager)
    var mViewPager: ViewPager? = null
    private var mBoomMenuButton: BoomMenuButton? = null

    @JvmField
    @BindView(R2.id.et_expression_input)
    var mExpressionInputEditText: EditText? = null

    @JvmField
    @BindView(R2.id.tv_expression_result)
    var mExpressionResultTextView: TextView? = null
    /**
     * tab页
     */
    private val mPageViewList: MutableList<View> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        initView()
    }

    /**
     * 界面初始化
     */
    private fun initView() {
        initTransparentStatusBar()
        initEditText()
        initNumericMenu()
        initBMB()
        initPageViews()
        initTabPages()
    }

    /**
     * 初始化透明状态栏
     */
    private fun initTransparentStatusBar() {
        //实现透明状态栏效果
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView = window.decorView
            val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            decorView.systemUiVisibility = option
            window.statusBarColor = Color.TRANSPARENT
        }
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = "强力计算器"
        }
    }

    /**
     * 初始化表达式输入框
     */
    private fun initEditText() {
        hideSoftKeyboardWithCursor()
        AutofitHelper.create(mExpressionInputEditText).setMinTextSize(20f).maxLines = 1
        mExpressionInputEditText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (TextUtils.isEmpty(s)) {
                    mExpressionResultTextView!!.text = ""
                    return
                }
                if (!sIsCalculating) {
                    calcExpresssion(false)
                }
            }

            override fun afterTextChanged(s: Editable) {
                run {
                    val matcher = Constants.OPERATORS_PATTERN.matcher(s)
                    while (matcher.find()) {
                        s.setSpan(
                            ForegroundColorSpan(-0x7e2b06),
                            matcher.start(),
                            matcher.end(),
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                    }
                }
                run {
                    val matcher = Pattern.compile("x").matcher(s)
                    while (matcher.find()) {
                        s.setSpan(
                            ForegroundColorSpan(-0xb704f),
                            matcher.start(),
                            matcher.end(),
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                    }
                }
                run {
                    val matcher = Constants.CONSTANTS_KEYWORDS1_PATTERN.matcher(s)
                    while (matcher.find()) {
                        s.setSpan(
                            ForegroundColorSpan(-0xa63),
                            matcher.start(),
                            matcher.end(),
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                    }
                }
                run {
                    val matcher = Constants.CONSTANTS_KEYWORDS2_PATTERN.matcher(s)
                    while (matcher.find()) {
                        s.setSpan(
                            ForegroundColorSpan(-0xa63),
                            matcher.start(),
                            matcher.end(),
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                    }
                }
                val matcher = Constants.FUNCTIONS_KEYWORDS_PATTERN.matcher(s)
                while (matcher.find()) {
                    s.setSpan(
                        ForegroundColorSpan(-0x5a2959),
                        matcher.start(),
                        matcher.end(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
                var startIndex = mExpressionInputEditText!!.selectionStart
                if (startIndex >= 2 && s.toString().substring(startIndex - 2, startIndex) == "()") {
                    startIndex--
                }
                mExpressionInputEditText!!.setSelection(startIndex)
            }
        })
    }

    /**
     * 初始化数字菜单
     */
    private fun initNumericMenu() {
        mNumericGridViewMenu!!.numColumns = 3
        val gridViewAdapter = GridViewAdapter(
            this,
            Arrays.asList(*Constants.NUMERIC_MENU_ITEM_TITLES),
            null,
            R.layout.cell_numeric
        )
        mNumericGridViewMenu!!.adapter = gridViewAdapter
        mNumericGridViewMenu!!.onItemClickListener =
            OnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
                if (Constants.NUMERIC_MENU_ITEM_TITLES[position] == "=") {
                    if (sIsCalculating) {
                        Snackbar.make(view!!, "请等待当前运算完成", Snackbar.LENGTH_SHORT)
                            .setAction("停止运算") { v: View? ->
                                ExpressionHelper.stopCalculate()
                                sIsCalculating = false
                            }.show()
                        return@OnItemClickListener
                    }
                    mExpressionResultTextView!!.text = "运算中..."

                    // 用户若点击"="按键，则保存此次运算结果
                    calcExpresssion(true)
                    return@OnItemClickListener
                }
                val inputString = Constants.NUMERIC_MENU_ITEM_TITLES[position]
                modifyExpressionInputEditView(inputString)
            }
    }

    /**
     * 初始化主界面右下角的BMB菜单按钮
     */
    private fun initBMB() {
        mBoomMenuButton = findViewById(R.id.bmb_menu)
        for (i in 0 until mBoomMenuButton!!.getPiecePlaceEnum().pieceNumber()) {
            val builder = TextOutsideCircleButton.Builder()
                .listener { index: Int -> handleBMBItemsClick(index) }
                .normalImageRes(Constants.BMB_MENU_ITEM_ICON_IDS[i]).normalText(
                    Constants.BMB_MENU_ITEM_TITLES[i]
                ).normalColor(Constants.BMB_MENU_ITEM_BG_COLORS[i])
            mBoomMenuButton!!.addBuilder(builder)
        }
    }

    /**
     * 初始化两个Tab页的网格布局
     */
    private fun initPageViews() {
        val constantsPageView = GridView(this)
        val functionsPageView = GridView(this)
        constantsPageView.numColumns = 3
        functionsPageView.numColumns = 3
        val constantsGridViewAdapter = GridViewAdapter(
            this,
            Arrays.asList(*Constants.CONSTANT_SYMBOLS),
            Arrays.asList(*Constants.CONSTANT_DESCRIPTIONS),
            R.layout.cell_constant
        )
        val functionsGridViewAdapter = GridViewAdapter(
            this,
            Arrays.asList(*Constants.FUNCTION_SYMBOLS),
            Arrays.asList(*Constants.FUNCTION_DESCRIPTIONS),
            R.layout.cell_function
        )
        constantsPageView.adapter = constantsGridViewAdapter
        functionsPageView.adapter = functionsGridViewAdapter
        constantsPageView.onItemClickListener =
            OnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
                modifyExpressionInputEditView(
                    Constants.CONSTANT_SYMBOLS[position]
                )
            }
        functionsPageView.onItemClickListener =
            OnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
                modifyExpressionInputEditView(if (Constants.FUNCTION_SYMBOLS[position] == "gamma") "Γ" else Constants.FUNCTION_SYMBOLS[position] + "()")
            }
        functionsPageView.onItemLongClickListener =
            OnItemLongClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
                val dialogBuilder = AlertDialog.Builder(this@MainActivity)
                dialogBuilder.setTitle(Constants.FUNCTION_SYMBOLS[position])
                dialogBuilder.setMessage(Constants.FUNCTIONS_HELP_MAP[Constants.FUNCTION_SYMBOLS[position]])
                dialogBuilder.setPositiveButton("确定", null)
                dialogBuilder.show()
                true
            }
        mPageViewList.add(constantsPageView)
        mPageViewList.add(functionsPageView)
    }

    /**
     * Tab页初始化
     */
    private fun initTabPages() {
        val myPageAdapter = MyPageAdapter(mPageViewList)
        mViewPager!!.adapter = myPageAdapter
        mTabBar!!.setupWithViewPager(mViewPager)
        mTabBar!!.getTabAt(0)!!.text = "常数"
        mTabBar!!.getTabAt(1)!!.text = "函数"
        mViewPager!!.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    mDrawer!!.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, GravityCompat.END)
                } else {
                    mDrawer!!.setDrawerLockMode(
                        DrawerLayout.LOCK_MODE_LOCKED_OPEN,
                        GravityCompat.END
                    )
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    /**
     * 修改表达式输入框的文字
     */
    private fun modifyExpressionInputEditView(str: String) {
        val startIndex = mExpressionInputEditText!!.selectionStart
        val endIndex = mExpressionInputEditText!!.selectionEnd
        if (startIndex == endIndex) {
            mExpressionInputEditText!!.text.insert(startIndex, str)
        } else {
            mExpressionInputEditText!!.text.replace(startIndex, endIndex, str)
        }
    }

    /**
     * 处理输入表达式的清空事件
     */
    private fun handleClearAll() {
        ExpressionHelper.stopCalculate()
        mExpressionInputEditText!!.setText("")
    }

    /**
     * 处理输入表达式的删除事件
     */
    private fun handleDelete() {
        val editable = mExpressionInputEditText!!.text
        val startIndex = mExpressionInputEditText!!.selectionStart
        val endIndex = mExpressionInputEditText!!.selectionEnd
        if (startIndex == endIndex) {
            if (startIndex == 0) {
                return
            }
            editable.delete(startIndex - 1, startIndex)
        } else {
            editable.delete(startIndex, endIndex)
        }
    }

    /**
     * 处理输入表达式运算结果的复制事件
     */
    private fun handleCopy(v: View) {
        val clipboardManager = v.context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clipData =
            ClipData.newPlainText("运算结果", mExpressionResultTextView!!.text.toString())
        clipboardManager.setPrimaryClip(clipData)
        Snackbar.make(v, "已复制运算结果", Snackbar.LENGTH_SHORT).show()
    }

    /**
     * 处理界面上的所有点击事件
     */
    @OnClick(
        R2.id.tv_clear_all,
        R2.id.tv_delete,
        R2.id.tv_copy,
        R2.id.iv_drawer_left_arrow,
        R2.id.tv_add,
        R2.id.tv_sub,
        R2.id.tv_mul,
        R2.id.tv_div,
        R2.id.tv_percent,
        R2.id.tv_power,
        R2.id.tv_degree,
        R2.id.tv_factorial,
        R2.id.tv_square_root,
        R2.id.tv_infinity,
        R2.id.tv_imaginary_num_unit,
        R2.id.tv_double_brace,
        R2.id.tv_comma,
        R2.id.tv_x_parameter
    )
    fun handleAllClick(v: View) {
        when (v.id) {
            R.id.tv_clear_all -> handleClearAll()
            R.id.tv_delete -> handleDelete()
            R.id.tv_copy -> handleCopy(v)
            R.id.iv_drawer_left_arrow -> mDrawer!!.openDrawer(GravityCompat.END)
            R.id.tv_add -> modifyExpressionInputEditView("+")
            R.id.tv_sub -> modifyExpressionInputEditView("-")
            R.id.tv_mul -> modifyExpressionInputEditView("×")
            R.id.tv_div -> modifyExpressionInputEditView("÷")
            R.id.tv_percent -> modifyExpressionInputEditView("%")
            R.id.tv_power -> modifyExpressionInputEditView("^")
            R.id.tv_degree -> modifyExpressionInputEditView("°")
            R.id.tv_factorial -> modifyExpressionInputEditView("!")
            R.id.tv_square_root -> modifyExpressionInputEditView("√")
            R.id.tv_infinity -> modifyExpressionInputEditView("∞")
            R.id.tv_imaginary_num_unit -> modifyExpressionInputEditView("i")
            R.id.tv_double_brace -> modifyExpressionInputEditView("()")
            R.id.tv_comma -> modifyExpressionInputEditView(",")
            R.id.tv_x_parameter -> modifyExpressionInputEditView("x")
        }
    }

    /**
     * 设置每个BMB菜单按钮的监听事件
     *
     * @param index 每个BMB菜单按钮的索引
     */
    private fun handleBMBItemsClick(index: Int) {
        when (index) {
            0 -> BigDecimalActivity.actionStart(this)
            1 -> RadixConversionActivity.actionStart(this)
            2 -> CapitalDecimalActivity.actionStart(this)
            3 -> GodModeActivity.actionStart(this)

            6 -> finish()
            else -> {}
        }
    }

    /**
     * 使得EditText有光标且点击后不弹出软键盘
     */
    private fun hideSoftKeyboardWithCursor() {
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        val currentVersion = Build.VERSION.SDK_INT

        // 4.0以上和4.2以上方法名有所改变
        var methodName: String? = null
        if (currentVersion >= 16) {
            // 4.2以上
            methodName = "setShowSoftInputOnFocus"
        } else if (currentVersion >= 14) {
            // 4.0以上
            methodName = "setSoftInputShownOnFocus"
        }
        val cls = EditText::class.java
        val setShowSoftInputOnFocus: Method
        try {
            setShowSoftInputOnFocus = cls.getMethod(methodName, Boolean::class.javaPrimitiveType)
            setShowSoftInputOnFocus.isAccessible = true
            setShowSoftInputOnFocus.invoke(mExpressionInputEditText, false)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        inputMethodManager.hideSoftInputFromWindow(mExpressionInputEditText!!.windowToken, 0)
    }

    /**
     * 计算用户输入的表达式
     *
     * @param isSave 是否保存运算结果
     */
    private fun calcExpresssion(isSave: Boolean) {
        mExpressionResultTextView!!.text = "运算中..."
        sIsCalculating = true
        val expression = mExpressionInputEditText!!.text.toString()
        Thread {
            val result = ExpressionHelper.calculate(expression)
            runOnUiThread {

                // 运算表达式语法正确
                if (result[1] == "false") {
                    if (isSave) {
                        // 保存运算结果
                        Constants.slastAnswerValue = result[0]
                    }

                    // 数值太大，跳转到大数运算结果界面查看结果
                    if (result[0].toByteArray().size > 1000) {
                        BigDecimalResultActivity.actionStart(this@MainActivity, result[0])
                        return@runOnUiThread
                    } else {
                        mExpressionResultTextView!!.text = result[0]
                    }
                } else {
                    mExpressionResultTextView!!.text = result[0]
                }
                sIsCalculating = false
            }
        }.start()
    }

    companion object {
        /**
         * 当前是否在运算中
         */
        private var sIsCalculating = false
    }
}