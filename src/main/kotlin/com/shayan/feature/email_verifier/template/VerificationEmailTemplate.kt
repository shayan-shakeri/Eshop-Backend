package feature.email_verifier.template

object VerificationEmailTemplate {

    fun create(
        username: String,
        code: String
    ): String {

        return """
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
        </head>

        <body style="
            margin:0;
            padding:0;
            background:#f4f7fb;
            font-family:Arial,Helvetica,sans-serif;
        ">

            <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td align="center" style="padding:40px 20px;">

                        <table
                            width="600"
                            cellpadding="0"
                            cellspacing="0"
                            style="
                                max-width:600px;
                                background:#ffffff;
                                border-radius:18px;
                                overflow:hidden;
                                box-shadow:0 8px 30px rgba(0,0,0,0.08);
                            "
                        >

                            <tr>
                                <td
                                    style="
                                        height:6px;
                                        background:#2563eb;
                                    "
                                ></td>
                            </tr>

                            <tr>
                                <td
                                    align="center"
                                    style="
                                        padding:40px 40px 20px;
                                    "
                                >
                                    <h1 style="
                                        margin:0;
                                        color:#111827;
                                        font-size:28px;
                                    ">
                                        Verify Your Email
                                    </h1>

                                    <p style="
                                        margin-top:12px;
                                        color:#6b7280;
                                        font-size:15px;
                                    ">
                                        Complete your account verification to continue.
                                    </p>
                                </td>
                            </tr>

                            <tr>
                                <td
                                    style="
                                        padding:0 40px;
                                        color:#374151;
                                        line-height:1.7;
                                        font-size:15px;
                                    "
                                >
                                    Hello <strong>$username</strong>,
                                    <br><br>

                                    We received a request to verify your email address.
                                    Use the verification code below to continue.
                                </td>
                            </tr>

                            <tr>
                                <td
                                    align="center"
                                    style="padding:35px 40px;"
                                >

                                    <div style="
                                        display:inline-block;
                                        background:#f8fafc;
                                        border:1px solid #e5e7eb;
                                        border-radius:14px;
                                        padding:18px 32px;
                                        font-size:34px;
                                        font-weight:700;
                                        letter-spacing:10px;
                                        color:#111827;
                                    ">
                                        $code
                                    </div>

                                </td>
                            </tr>

                            <tr>
                                <td
                                    style="
                                        padding:0 40px;
                                        color:#374151;
                                        font-size:15px;
                                        line-height:1.7;
                                    "
                                >
                                    This verification code will expire in
                                    <strong>2 minutes</strong>.

                                    <br><br>

                                    For your security, never share this code with anyone.
                                </td>
                            </tr>

                            <tr>
                                <td
                                    style="
                                        padding:30px 40px;
                                    "
                                >
                                    <div style="
                                        background:#f9fafb;
                                        border-left:4px solid #2563eb;
                                        padding:16px;
                                        border-radius:8px;
                                        color:#6b7280;
                                        font-size:14px;
                                    ">
                                        If you didn't request this verification,
                                        you can safely ignore this email.
                                    </div>
                                </td>
                            </tr>

                            <tr>
                                <td
                                    align="center"
                                    style="
                                        border-top:1px solid #e5e7eb;
                                        padding:25px;
                                        color:#9ca3af;
                                        font-size:12px;
                                    "
                                >
                                    © 2026 EShop
                                    <br>
                                    This is an automated email. Please do not reply.
                                </td>
                            </tr>

                        </table>

                    </td>
                </tr>
            </table>

        </body>
        </html>
        """.trimIndent()
    }
}