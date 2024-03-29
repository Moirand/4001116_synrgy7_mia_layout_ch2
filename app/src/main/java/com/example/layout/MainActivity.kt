package com.example.layout

import addDot
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import autoCalculateTip
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import removeDot

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var edtCostOfService: TextInputEditText
    private lateinit var edtTip: TextInputEditText
    private lateinit var cgTip: ChipGroup
    private lateinit var cbRoundUpTip: MaterialCheckBox
    private lateinit var btnCalculate: MaterialButton
    private lateinit var tvCostAmount: MaterialTextView
    private lateinit var tvTipAmount: MaterialTextView
    private lateinit var tvTotalAmount: MaterialTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        cgTip = findViewById(R.id.chipgroup)
        edtTip = findViewById(R.id.edt_tip)
        btnCalculate = findViewById(R.id.btn_calculate)
        cbRoundUpTip = findViewById(R.id.cb_round_up_tip)
        edtCostOfService = findViewById(R.id.edt_cost_of_service)
        tvCostAmount = findViewById(R.id.tv_cost_value)
        tvTipAmount = findViewById(R.id.tv_tip_value)
        tvTotalAmount = findViewById(R.id.tv_total_value)

        edtCostOfService.addTextChangedListener(TextWatherBuatanSaya(edtCostOfService))
        edtTip.addTextChangedListener(TextWatherBuatanSaya(edtTip))

        cgTip.setOnCheckedChangeListener { checkGroup, checkedId ->
            val chip: Chip? = checkGroup.findViewById(checkedId)
            chip?.let {
                when (chip.id) {
                    R.id.chip_5percent -> edtTip.setText(
                        autoCalculateTip(
                            edtCostOfService.text.toString(),
                            5
                        )
                    )

                    R.id.chip_10percent -> edtTip.setText(
                        autoCalculateTip(
                            edtCostOfService.text.toString(),
                            10
                        )
                    )

                    R.id.chip_15percent -> edtTip.setText(
                        autoCalculateTip(
                            edtCostOfService.text.toString(),
                            15
                        )
                    )

                    R.id.chip_20percent -> edtTip.setText(
                        autoCalculateTip(
                            edtCostOfService.text.toString(),
                            20
                        )
                    )
                }
            }
        }

        btnCalculate.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val costOfService =
            if (edtCostOfService.text.toString() == "") "0" else removeDot(edtCostOfService.text.toString())
        val tipAmount = if (edtTip.text.toString() == "") "0" else removeDot(edtTip.text.toString())

        tvCostAmount.text = "Rp ${addDot(removeDot(costOfService))}"
        // Belum ada fitur pembulatan tip ke atas
        tvTipAmount.text = "Rp ${addDot(removeDot(tipAmount))}"

        val totalAmount = costOfService.toLong() + tipAmount.toLong()
        tvTotalAmount.text = "Rp ${addDot(totalAmount.toString())}"
    }
}