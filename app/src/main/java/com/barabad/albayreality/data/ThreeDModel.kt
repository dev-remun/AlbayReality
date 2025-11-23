package com.barabad.albayreality.data

/*
* This class will serve as the blueprint to create the object for the 3d models.
* The creation of the 3d models should be done in the MainActivity.
* */

class ThreeDModel() {

    private var qr_code: String? = null
    private var name: String? = null
    private var location: String? = null
    private var description: String? = null
    private var img_filepath: String? = null
    private var model_filepath: String? = null

    // Secondary constructor
    constructor(
        qr_code: String,
        name: String,
        location: String,
        description: String,
        // img_filepath: String,
        // model_filepath: String
    ) : this() {
        this.qr_code = qr_code
        this.name = name
        this.location = location
        this.description = description
        // this.img_filepath = img_filepath
        // this.model_filepath = model_filepath
    }

    // Setters
    fun setQRCode(qr_code: String) {
        this.qr_code = qr_code
    }

    fun setName(name: String) {
        this.name = name
    }

    fun setLocation(location: String) {
        this.location = location
    }

    fun setDescription(description: String) {
        this.description = description
    }

    fun setImgFilePath(file_path: String) {
        this.img_filepath = file_path
    }

    fun setModelFilepath(file_path: String) {
        this.model_filepath = file_path
    }

    // Getters
    fun getQRCode(): String? = qr_code
    fun getName(): String? = name
    fun getLocation(): String? = location
    fun getDescription(): String? = description
    fun getImgFilepath(): String? = img_filepath
    fun getModelFilepath(): String? = model_filepath
}
