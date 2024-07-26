import { useSelector } from "react-redux";
import { Navigate } from "react-router-dom";

function EmployeeRoute({ children }) {
    const authState = useSelector((store) => store.auth);
    if (!authState.role || authState.role === "EMPLOYEE") {
      return <Navigate to={"/"} />;
    }
    return children;
  }
  
  export default EmployeeRoute;