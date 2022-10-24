package ru.ekitselyuk.kotlinforbegginers.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import java.text.Normalizer
import java.util.*
import kotlin.collections.ArrayList

class IgnoreAccentsArrayAdapter<T>(
    private val context: Context,
    private val textViewResourceId: Int,
    private val mObjects: MutableList<T>,
    private val mFieldId: Int = 0,
) : BaseAdapter(), Filterable {

    private val mLock = Any()
    private val mOriginalValues: ArrayList<T> = ArrayList(mObjects)
    private val mFilter: ArrayFilter by lazy { ArrayFilter() }
    private val mInflater: LayoutInflater by lazy { context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater }

    override fun getCount(): Int {
        return mObjects.size
    }

    override fun getItem(position: Int): T {
        return mObjects[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createViewFromResource(position, convertView, parent)
    }

    private fun createViewFromResource(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val text: TextView
        val view: View = convertView ?: mInflater.inflate(textViewResourceId, parent, false)
        text = try {
            if (mFieldId == 0) {
                //  If no custom field is assigned, assume the whole resource is a TextView
                view as TextView
            } else {
                //  Otherwise, find the TextView field within the layout
                view.findViewById<View>(mFieldId) as TextView
            }
        } catch (e: ClassCastException) {
            Log.e("ArrayAdapter", "You must supply a resource ID for a TextView")
            throw IllegalStateException(
                "ArrayAdapter requires the resource ID to be a TextView", e
            )
        }
        text.text = getItem(position).toString()
        return view
    }

    override fun getFilter(): Filter {
        return mFilter
    }

    private inner class ArrayFilter : Filter() {
        override fun performFiltering(prefix: CharSequence?): FilterResults {
            val results = FilterResults()
            if (prefix == null || prefix.isEmpty()) {
                synchronized(mLock) {
                    val list = mOriginalValues.toList()
                    results.values = list
                    results.count = list.size
                }
            } else {
                val prefixString = prefix.toString().lowercase(Locale.getDefault())
                val values = mOriginalValues.toList()
                val count = values.size
                val newValues = ArrayList<T>(count)
                for (i in 0 until count) {
                    val value = values[i]
                    val valueText = value.toString().lowercase(Locale.getDefault())
                    val valueTextNoPalatals = stripAccents(valueText)
                    val prefixStringNoPalatals = stripAccents(prefixString)

                    if (valueTextNoPalatals.contains(prefixStringNoPalatals)) {
                        newValues.add(value)
                    } else {
                        val words = valueText.split(" ".toRegex()).dropLastWhile { it.isEmpty() }
                            .toTypedArray()
                        val wordCount = words.size
                        for (k in 0 until wordCount) {
                            val valueWordNoPalatals = stripAccents(
                                words[k]
                            )
                            if (valueWordNoPalatals.contains(prefixStringNoPalatals)) {
                                newValues.add(value)
                                break
                            }
                        }
                    }
                }
                results.values = newValues
                results.count = newValues.size
            }
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            results?.let {
                mObjects.apply {
                    clear()
                    addAll(results.values as List<T>)
                }
                if (results.count > 0) {
                    notifyDataSetChanged()
                } else {
                    notifyDataSetInvalidated()
                }
            }
        }
    }

    companion object {
        private fun stripAccents(s: String): String =
            Normalizer.normalize(s, Normalizer.Form.NFD)
                .replace("\\p{InCombiningDiacriticalMarks}".toRegex(), "")
    }
}