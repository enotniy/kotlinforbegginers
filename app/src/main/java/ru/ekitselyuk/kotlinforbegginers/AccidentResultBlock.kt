package ru.ekitselyuk.kotlinforbegginers

import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IFlexible
import eu.davidea.viewholders.FlexibleViewHolder

data class AccidentResultBlock(
    val blockName: String,
    val vekData: VekManager? = null,
    val reportReq: SaveReportReq.MessageClass? = null,
    val isVek: Boolean = false,
    val isReport: Boolean = false,
    val eventType: String? = null
) :
    AbstractFlexibleItem<AccidentResultBlock.ViewHolder>() {

    override fun getLayoutRes(): Int {
        return R.layout.main_item
    }

    override fun createViewHolder(
        view: View,
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>
    ): ViewHolder {
        return ViewHolder(view, adapter)
    }

    override fun bindViewHolder(
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>?,
        hld: ViewHolder?,
        position: Int,
        payloads: MutableList<Any>?
    ) {
        if (hld == null) {
            return
        }

        if (isVek) {
            vekData?.vekData?.vekData?.AccidentResult?.let { accidentResult ->
                accidentResult.VehicleCount?.let {
                    hld.edtVehicleCount.setText(it.toString())
                    hld.lytVehicleInRunningOrder.showIf { it == 2 }
                }
                accidentResult.VehicleInRunningOrder?.let {
                    if (it) {
                        hld.rbVehicleInRunningOrderYes.isChecked = true
                    } else {
                        hld.rbVehicleInRunningOrderNo.isChecked = true
                    }

                    hld.lytIsDamaged3PersonProperty.showIf { it }
                }
                accidentResult.ClimbedOver?.let {
                    when (it) {
                        1 -> {
                            hld.rbClimbedOver1.isChecked = true
                            hld.lytIsDamaged3PersonProperty.showIf { true }
                        }
                        2 -> {
                            hld.rbClimbedOver2.isChecked = true
                        }
                        0 -> {
                            hld.rbClimbedOver3.isChecked = true
                        }
                    }

                }
                accidentResult.IsDamaged3PersonProperty?.let {
                    if (it) {
                        hld.rbIsDamaged3PersonPropertyYes.isChecked = true
                    } else {
                        hld.rbIsDamaged3PersonPropertyNo.isChecked = true
                        hld.lytIsAgree.showIf { true }
                    }
                }


                accidentResult.IsAgree?.let {
                    if (it) {
                        hld.rbIsAgreeYes.isChecked = true
                    } else {
                        hld.rbIsAgreeNo.isChecked = true
                    }
                }
                accidentResult.AgreedStatement?.let {
                    hld.cbAgreedStatement.isChecked = it
                }
            }
        }

        hld.lytVehicleCount.showIf {
            eventType == "1.1.1." ||
                    eventType == "1.1.2.1." ||
                    eventType == "1.1.2.4." ||
                    eventType == "1.1.2.5." ||
                    eventType == "1.1.3." ||
                    eventType == "1.1.6." ||
                    eventType == "1.1.9."
        }

        hld.lytIsDamaged3PersonProperty.showIf { eventType == "1.1.2.5." }
        hld.lytClimbedOver.showIf { eventType == "1.1.2.4." }

        if (eventType == "1.1.2.4.") {
            hld.lytIsDamaged3PersonProperty.showIf { hld.rbClimbedOver1.isChecked }
        }

        hld.cbAgreedStatement.showIf { eventType == "1.1.7." }

        hld.edtVehicleCount.addTextChangedListener {
            if (it.toString() == "2") {
                if (eventType == "1.1.1." || eventType == "1.1.2.1.") {
                    hld.lytVehicleInRunningOrder.showIf { true }
                    hld.lytIsDamaged3PersonProperty.showIf { hld.rbVehicleInRunningOrderYes.isChecked }

                    if (hld.rbVehicleInRunningOrderYes.isChecked) {
                        hld.lytIsAgree.showIf { hld.rbIsDamaged3PersonPropertyNo.isChecked }
                        hld.cbAgreedStatement.showIf { hld.rbIsDamaged3PersonPropertyNo.isChecked }
                    }
                }
            } else {
                hld.lytVehicleInRunningOrder.showIf { false }
                hld.lytIsDamaged3PersonProperty.showIf { false }
                hld.lytIsAgree.showIf { false }
            }

            vekData?.vekData?.vekData?.AccidentResult?.VehicleCount =
                it.toString().toIntOrNull()
        }

        hld.rbParentVehicleInRunningOrder.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb_VehicleInRunningOrder_Yes -> {
                    if (eventType == "1.1.1." || eventType == "1.1.2.1.") {
                        hld.lytIsDamaged3PersonProperty.showIf { true }
                    }
                    vekData?.vekData?.vekData?.AccidentResult?.VehicleInRunningOrder = true
                }
                R.id.rb_VehicleInRunningOrder_No -> {
                    hld.lytIsDamaged3PersonProperty.showIf { false }
                    vekData?.vekData?.vekData?.AccidentResult?.VehicleInRunningOrder = false
                }
            }
        }

        hld.rbParentClimbedOver.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb_ClimbedOver_1 -> {
                    hld.lytIsDamaged3PersonProperty.showIf { eventType == "1.1.2.4." }
                    vekData?.vekData?.vekData?.AccidentResult?.ClimbedOver = 1
                }
                R.id.rb_ClimbedOver_2 -> {
                    hld.lytIsDamaged3PersonProperty.showIf { false }
                    vekData?.vekData?.vekData?.AccidentResult?.ClimbedOver = 2
                }
                R.id.rb_ClimbedOver_3 -> {
                    hld.lytIsDamaged3PersonProperty.showIf { false }
                    vekData?.vekData?.vekData?.AccidentResult?.ClimbedOver = 3
                }
            }
        }

        hld.rbParentIsAgree.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_IsAgree_Yes -> {
                    hld.cbAgreedStatement.isEnabled = true
                    vekData?.vekData?.vekData?.AccidentResult?.IsAgree = true
                }
                R.id.rb_IsAgree_No -> {
                    hld.cbAgreedStatement.isChecked = false
                    hld.cbAgreedStatement.isEnabled = false
                    vekData?.vekData?.vekData?.AccidentResult?.IsAgree = false
                }
            }
        }

        hld.rbParentIsDamaged3PersonProperty.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb_IsDamaged3PersonProperty_Yes -> {
                    hld.lytIsAgree.showIf { false }
                    vekData?.vekData?.vekData?.AccidentResult?.IsDamaged3PersonProperty = true
                }
                R.id.rb_IsDamaged3PersonProperty_No -> {
                    hld.lytIsAgree.showIf { eventType == "1.1.1." || eventType == "1.1.2.1." }
                    vekData?.vekData?.vekData?.AccidentResult?.IsDamaged3PersonProperty = false
                }
            }
        }

    }

    class ViewHolder(view: View, adapter: FlexibleAdapter<*>) : FlexibleViewHolder(view, adapter) {

        val lytVehicleCount: LinearLayout = view.findViewById(R.id.lyt_VehicleCount)
        val edtVehicleCount: EditText = view.findViewById(R.id.edt_VehicleCount)

        val lytVehicleInRunningOrder: LinearLayout =
            view.findViewById(R.id.lyt_VehicleInRunningOrder)
        val rbParentVehicleInRunningOrder: RadioGroup =
            view.findViewById(R.id.rbParent_VehicleInRunningOrder)
        val rbVehicleInRunningOrderYes: AppCompatRadioButton =
            view.findViewById(R.id.rb_VehicleInRunningOrder_Yes)
        val rbVehicleInRunningOrderNo: AppCompatRadioButton =
            view.findViewById(R.id.rb_VehicleInRunningOrder_No)

        val lytClimbedOver: LinearLayout = view.findViewById(R.id.lyt_ClimbedOver)
        val rbParentClimbedOver: RadioGroup = view.findViewById(R.id.rbParent_ClimbedOver)
        val rbClimbedOver1: AppCompatRadioButton = view.findViewById(R.id.rb_ClimbedOver_1)
        val rbClimbedOver2: AppCompatRadioButton = view.findViewById(R.id.rb_ClimbedOver_2)
        val rbClimbedOver3: AppCompatRadioButton = view.findViewById(R.id.rb_ClimbedOver_3)

        val lytIsDamaged3PersonProperty: LinearLayout =
            view.findViewById(R.id.lyt_IsDamaged3PersonProperty)
        val rbParentIsDamaged3PersonProperty: RadioGroup =
            view.findViewById(R.id.rbParent_IsDamaged3PersonProperty)
        val rbIsDamaged3PersonPropertyNo: AppCompatRadioButton =
            view.findViewById(R.id.rb_IsDamaged3PersonProperty_No)
        val rbIsDamaged3PersonPropertyYes: AppCompatRadioButton =
            view.findViewById(R.id.rb_IsDamaged3PersonProperty_Yes)

        val lytIsAgree: LinearLayout = view.findViewById(R.id.lyt_IsAgree)
        val rbParentIsAgree: RadioGroup = view.findViewById(R.id.rbParent_IsAgree)
        val rbIsAgreeNo: AppCompatRadioButton = view.findViewById(R.id.rb_IsAgree_No)
        val rbIsAgreeYes: AppCompatRadioButton = view.findViewById(R.id.rb_IsAgree_Yes)

        val cbAgreedStatement: AppCompatCheckBox = view.findViewById(R.id.cb_AgreedStatement)
    }
}


class VekManager {

    var vekData: VekData? = null

    class VekData {
        var vekData: VekDataInner? = null

        class VekDataInner {
            var AccidentResult: AccidentResultClass? = null

            class AccidentResultClass {
                var VehicleCount: Int? = null
                var VehicleInRunningOrder: Boolean? = null
                var ClimbedOver: Int? = null
                var IsDamaged3PersonProperty: Boolean? = null
                var IsAgree: Boolean? = null
                var AgreedStatement: Boolean? = null
            }
        }
    }
}

class SaveReportReq {
    class MessageClass
}

enum class EventType(val version: String) {
    EVENT_1_1_1("1.1.1.")
}

class VekReportSelector(
    val isVek: Boolean = true,
    val vekData: VekManager? = null,
    val reportReq: SaveReportReq.MessageClass? = null
) {
}

inline fun View.showIf(block: () -> Boolean) {
    visibility = if (block()) View.VISIBLE else View.GONE
}