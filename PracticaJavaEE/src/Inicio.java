import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Inicio extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	private String user;
	private String password;
	private ServletContext context;
	String nextPage = "";

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		context = config.getServletContext();
		user = context.getInitParameter("usuario");
		password = context.getInitParameter("password");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		HttpSession session = request.getSession(true);

		String result = session.getAttribute("user").toString();

		if (result != null) {
			nextPage = "/registrado.jsp";
		} else {
			String usuario = request.getParameter("usuario");
			String contraseña = request.getParameter("password");
			
			if (usuario != null && contraseña != null) {
				
				if (usuario == "darya" && contraseña == "darya") {
					nextPage = "/registrado.jsp";
				} else {
					nextPage = "/autorizar.html";
				}				
			}
			else {
				nextPage = "/autorizar.html";
			}
		}

		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}
}