package com.example.hourlance

data class ClientClass(
    val name: String,
    val projectType: String?,
    val rate: Double,
    val overtimeRate: Double?,
    val contactName: String,
    val contactPhone: Int,
    val contactEmail: String,
    val contactAddress: String,
    val contactState: String,
    val contactWebsite: String? ) {

    /* Required Data
    var clientName: String = name
    var clientRate: Double = rate
    var clientContactName: String = contactName
    var clientContactPhone: Int = contactPhone
    var clientContactEmail: String = contactEmail
    var clientContactAddress: String = contactAddress
    var clientContactState: String = contactState

    // Optional Data
    var clientProjectType: String? = projectType
    var clientOvertimeRate: Double? = overtimeRate
    var clientContactWebsite: String? = contactWebsite
     */
}
