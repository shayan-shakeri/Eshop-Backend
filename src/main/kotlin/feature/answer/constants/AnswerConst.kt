package com.shayan.feature.answer.constants

object AnswerConst {

    const val TABLE_NAME = "answer"

    const val ID = "id"
    const val USER_ID = "user_id"
    const val QUESTION_COMMENT_ID = "question_comment_id"
    const val TYPE = "type"
    const val CONTENT = "content"

    // Numeral
    const val CONTENT_LENGTH = 255

    // Route
    const val MAIN_ROUTE = "/answer"

    const val ADD_ROUTE = "/add"
    const val UPDATE_ROUTE = "/update"
    const val DELETE_ROUTE = "/delete"

    private const val QUESTION_COMMENT_ID_ROUTE =
        "/{questionCommentId}"

    const val READ_ROUTE =
        "/read$QUESTION_COMMENT_ID_ROUTE"

    const val QUESTION_COMMENT_ID_PARAM =
        "questionCommentId"

    // Action
    const val ADD_ACTION =
        "User added an answer"

    const val UPDATE_ACTION =
        "User updated an answer"

    const val DELETE_ACTION =
        "User deleted an answer"
}