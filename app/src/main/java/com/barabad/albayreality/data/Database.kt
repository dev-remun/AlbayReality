package com.barabad.albayreality.data

/*
* The class to create a Database.
* To actual object of this Database is already in the DatabaseProvider.
* Please refer to the DatabaseProvider.
* */
class Database {

    private var models: Array<ThreeDModel?> = arrayOfNulls(100) // fixed size like Java
    private var modelCount: Int = 0

    // Setters
    fun addModel(model: ThreeDModel) {
        models[modelCount] = model
        modelCount++
    }

    // Getters
    fun getModelByQRCode(qrCode: String): ThreeDModel? {

        if (isEmpty()) {
            println("Database is empty")
            return null
        }

        for (i in 0 until modelCount) {
            if (models[i]?.getQRCode() == qrCode) {
                return models[i]
            }
        }

        return null
    }

    // Utilities
    fun isEmpty(): Boolean {
        return modelCount == 0
    }
}
