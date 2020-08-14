package com.example.caffo.model

data class ContentDTO(var diaryScript : String? = null,
                      var imageUri: String? = null,
                      var uid : String? = null,
                      var userId : String? = null,
                      var timestamp : Long? = null,
                      var favoriteCount : Int = 0)