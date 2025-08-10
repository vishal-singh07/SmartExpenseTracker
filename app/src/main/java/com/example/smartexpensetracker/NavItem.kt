package com.example.smartexpensetracker

import androidx.annotation.DrawableRes

/**
 * Enum class representing the navigation items in the application.
 * Each navigation item has a route, label, and an optional icon resource.
 *
 * @property route The route string used for navigation.
 * @property label The label displayed for the navigation item.
 * @property icon The drawable resource ID for the icon associated with the navigation item (optional).
 */
enum class NavItem(
    val route: String,
    val label: String,
    @DrawableRes val icon: Int? = null
) {
    /**
     * Navigation item for the "Entry" screen.
     * @route "entry"
     * @label "Entry"
     * @icon R.drawable.ic_entry
     */
    Entry("entry", "Entry", R.drawable.ic_entry),

    /**
     * Navigation item for the "List" screen.
     * @route "list"
     * @label "List"
     * @icon R.drawable.ic_list
     */
    List("list", "List", R.drawable.ic_list),

    /**
     * Navigation item for the "Report" screen.
     * @route "report"
     * @label "Report"
     * @icon R.drawable.ic_report
     */
    Report("report", "Report", R.drawable.ic_report),

    /**
     * Navigation item for the "Settings" screen.
     * @route "setting"
     * @label "Settings"
     * @icon R.drawable.ic_setting
     */
    Setting("setting", "Settings", R.drawable.ic_setting)
}