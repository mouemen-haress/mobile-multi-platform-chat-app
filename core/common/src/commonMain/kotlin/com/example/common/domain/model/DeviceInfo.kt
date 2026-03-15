package com.example.common.domain.model

data class DeviceInfo(
    var physicalDeviceId: String? = null,
    var deviceName: String? = null,
    var deviceType: String? = null,
    var manufacturer: String? = null,
    var serialNumber: String? = null,
    var deviceModel: String? = null,
    var deviceSoftware: String? = null,
    var deviceSoftwareVersion: String? = null,
    var middlewareVersion: String? = null,
    var applicationVersion: String? = null,
    var caDeviceId: String? = null,
    var caDeviceType: String? = null

)
