//package core.util
//
//import java.io.File
//
//fun main() {
//    val basePackage = "src/main/kotlin/feature"
//    val testPackage = "src/test/kotlin/feature"
//
//    val tables = listOf(
//        "users", "user_pic", "audit_logs", "users_session", "address",
//        "search_history", "category", "filter", "product", "setting",
//        "product_image", "orders", "order_product", "comment", "question",
//        "answer", "favorites", "discount", "support_chat", "support_message",
//        "role", "employee", "employee_audit_log", "version_control",
//        "error_log", "banner", "monthly_log"
//    )
//
//    val mainFolders = listOf("constants", "table", "model", "dto", "mapper", "repository", "service", "route")
//
//    println("🚀 Starting to build the enterprise structure...")
//
//    tables.forEach { table ->
//        // Generate Main Folders
//        mainFolders.forEach { folder ->
//            File("$basePackage/$table/$folder").mkdirs()
//        }
//    }
//
//    println("✅ Done! Your enterprise structure is ready.")
//    println("Main: $basePackage")
//    println("Test: $testPackage")
//}
