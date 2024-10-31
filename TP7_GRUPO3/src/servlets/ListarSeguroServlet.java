package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImpl.SeguroDaoImpl;
import daoImpl.TipoSeguroDaoImpl;
import dominio.Seguro;
import dominio.TipoSeguro;



@WebServlet("/ListarSeguroServlet")
public class ListarSeguroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ListarSeguroServlet() {
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TipoSeguroDaoImpl daoTipo = new TipoSeguroDaoImpl();
		List<TipoSeguro> tiposSeguros =  daoTipo.readAll();
		
		
		SeguroDaoImpl dao = new SeguroDaoImpl();
		List<Seguro> seguros;
		
		if (request.getParameter("slcTipo") != null && !request.getParameter("slcTipo").isEmpty()) {
			int tipo = Integer.parseInt(request.getParameter("slcTipo").toString());
			seguros = dao.filtrarSegunTipo(tipo);
		} else {
			seguros = dao.readAll();
		}
		
		request.setAttribute("seguros", seguros);
		request.setAttribute("tiposSeguros", tiposSeguros);

        // Redirigir a ListarSeguros.jsp
        RequestDispatcher rd = request.getRequestDispatcher("/ListarSeguros.jsp");   
        rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
