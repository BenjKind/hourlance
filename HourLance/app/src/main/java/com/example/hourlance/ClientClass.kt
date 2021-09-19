package com.example.hourlance

class ClientClass(
    name: String,
    projectType: String,
    rate: Double,
    overtimeRate: Double,
    contactName: String,
    contactPhone: Int,
    contactEmail: String,
    contactAddress: String,
    contactState: String,
    contactWebsite: String ) {

    // Required Data
    var clientName: String = name
    var clientRate: Double = rate
    var clientContactName: String = contactName
    var clientContactPhone: Int = contactPhone
    var clientContactEmail: String = contactEmail
    var clientContactAddress: String = contactAddress
    var clientContactState: String = contactState

    // Optional Data
    var clientProjectType: String = ""
    var clientOvertimeRate: Double = 0.0
    var clientContactWebsite: String = ""
}