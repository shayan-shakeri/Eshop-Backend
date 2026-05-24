package core.exception

class Forbidden(onlyEmployeeHasAccess: Boolean): Exception("Forbidden, only ${if (onlyEmployeeHasAccess) "employee" else "user"} has access to this part")