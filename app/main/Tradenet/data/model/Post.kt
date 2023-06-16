package com.c23_ps162.trade_net.data.model

import com.google.gson.annotations.SerializedName

data class Post(

	@field:SerializedName("creatorProfile")
	val creatorProfile: CreatorProfile? = null,

	@field:SerializedName("content")
	val content: Content? = null
)

data class CreatorProfile(

	@field:SerializedName("userProfilePictureSmallURL")
	val userProfilePictureSmallURL: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null
)

data class Content(

	@field:SerializedName("priceItemPrintAble")
	val priceItemPrintAble: String? = null,

	@field:SerializedName("imageURL")
	val imageURL: String? = null,

	@field:SerializedName("contentId")
	val contentId: String? = null,

	@field:SerializedName("caption")
	val caption: String? = null,

	@field:SerializedName("likeCount")
	val likeCount: Int? = null,

	@field:SerializedName("priceItem")
	val priceItem: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("liked")
	val liked: Boolean? = null,

	@field:SerializedName("commentCount")
	val commentCount: Int? = null
)
