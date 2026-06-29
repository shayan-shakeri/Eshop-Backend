package com.shayan.feature.support_message.constants

object SupportMessageConst {

    const val TABLE_NAME = "support_message"

    const val ID = "id"
    const val USER_ID = "user_id"
    const val SUPPORT_CHAT_ID = "support_chat_id"
    const val CONTENT = "content"
    const val TITLE = "title"
    const val MESSAGE_TYPE = "message_type"
    const val SEQUENCE = "sequence"

    // numerical
    const val TITLE_LENGTH = 40

    // route params
    const val CHAT_ID_PARAM = "chatId"

    // route
    const val MAIN_ROUTE = "/support-message"

    const val IMAGE_ROUTE = "$MAIN_ROUTE/image"

    const val WS_ROUTE =
        "/ws/{$CHAT_ID_PARAM}"

    const val ADD_TEXT_ROUTE =
        "/add/text"

    const val ADD_IMAGE_ROUTE =
        "/add/image"

    const val READ_ROUTE =
        "/read/{$CHAT_ID_PARAM}"

    const val DELETE_ROUTE =
        "/delete"

    // image
    const val FILE_PATH =
        "uploads/support"

    const val REMOTE_PATH =
        "/image"

    const val CHAT_ID =
        "chatId"

    const val MISSING_CHAT_ID =
        "Support chat id is required"


    const val MISSING_FILE =
        "Image file is required"


}