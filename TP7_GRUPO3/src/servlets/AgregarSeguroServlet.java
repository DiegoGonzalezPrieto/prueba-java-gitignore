package servlets;

import java.io.IOException;
import java.util.ArrayList;

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

@WebServlet("/AgregarSeguroServlet")
public class AgregarSeguroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<TipoSeguro> listaTipoSeguros;
	private TipoSeguroDaoImpl tipoSeguroDao;
	private SeguroDaoImpl seguroDao = new SeguroDaoImpl();
   
    public AgregarSeguroServlet() {
        super();
        tipoSeguroDao = new TipoSeguroDaoImpl();
    }

	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		obtenerLista();
		request.setAttribute("tipos", listaTipoSeguros);
		//request.setAttribute("listaTipoSeguros", listaTipoSeguros);
		//request.getRequestDispatcher("/AgregarSeguro.jsp").forward(request, response);

		if (request.getParameter("btnEnviar") != null) {
            Seguro seguro  = new Seguro();
            seguro.setDescripcion((request.getParameter("txtDescripcion")));
            int tipoSeguroId = Integer.parseInt(request.getParameter("Tipos"));
            seguro.setTipo(new TipoSeguro(tipoSeguroId, null)); 
            seguro.setCostoContratacion(Float.parseFloat(request.getParameter("txtCostoContratacion")));
            seguro.setCostoAsegurado(Float.parseFloat(request.getParameter("txtCostoMaximo")));

            boolean filas = seguroDao.insert(seguro);            		
            request.setAttribute("cantFilas", filas ? 1 : 0);
		}
            
		RequestDispatcher rd = request.getRequestDispatcher("/AgregarSeguro.jsp");   
	        rd.forward(request, response);
	}*/

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        obtenerLista();
        request.setAttribute("tipos", listaTipoSeguros);

        // Obtener el próximo ID
        int proximoId = seguroDao.ultimoSeguro() + 1;
        request.setAttribute("proximoId", proximoId);

        // Redirigir a AgregarSeguro.jsp
        RequestDispatcher rd = request.getRequestDispatcher("/AgregarSeguro.jsp");   
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("btnEnviar") != null) { 
            String mensaje = "";
            try {
                if (validarDatos(request)) {
                    //System.out.println("Datos validados");
                    
                    // Leer los datos
                    String descripcion = request.getParameter("txtDescripcion");
                    int tipoSeguroId = Integer.parseInt(request.getParameter("Tipos"));
                    float costoContratacion = Float.parseFloat(request.getParameter("txtCostoContratacion"));
                    float costoAsegurado = Float.parseFloat(request.getParameter("txtCostoMaximo"));

                    // Crear el objeto Seguro
                    Seguro seguro = new Seguro(descripcion, new TipoSeguro(tipoSeguroId), costoContratacion, costoAsegurado);

                    // Insertar en bBD
                    boolean filas = seguroDao.insert(seguro);
                    //System.out.println("Inserción a BD realizada ok");

                    // Enviar mensaje de confirmación o error
                    if (filas) {
                        mensaje = "¡Seguro agregado exitosamente!";
                        System.out.println("¡Seguro agregado exitosamente!");
                    } else {
                        mensaje = "Error al agregar seguro";
                        System.err.println("Error al agregar seguro");
                    }
                } else {
                    mensaje = (String) request.getAttribute("mensaje");
                    System.err.println("Validación fallida: " + mensaje);
                }
            } catch (Exception ex) {
                mensaje = "Error al agregar seguro: " + ex.getMessage();
                System.err.println("Error al agregar seguro: " + ex.getMessage());
                ex.printStackTrace();
            }

            // Redirigir a AgregarSeguro.jsp (o debería hacerlo a Listar? a Inicio??)
            request.setAttribute("mensaje", mensaje);
        }
        doGet(request, response);
    }

    private void obtenerLista() {
        this.listaTipoSeguros = (ArrayList<TipoSeguro>) tipoSeguroDao.readAll();
    }

    private boolean validarDatos(HttpServletRequest request) {
        String descripcion = request.getParameter("txtDescripcion");
        String tipoSeguroStr = request.getParameter("Tipos");
        String costoContratacionStr = request.getParameter("txtCostoContratacion");
        String costoAseguradoStr = request.getParameter("txtCostoMaximo");

        if (descripcion == null || descripcion.trim().isEmpty()) {
            request.setAttribute("mensaje", "La descripción es obligatoria.");
            //System.err.println("La descripción es obligatoria.");
            return false;
        } 
        if (tipoSeguroStr == null || tipoSeguroStr.trim().isEmpty()) {
            request.setAttribute("mensaje", "Debe seleccionar un tipo de seguro.");
            //System.err.println("Debe seleccionar un tipo de seguro.");
            return false;
        } 
        if (costoContratacionStr == null || costoContratacionStr.trim().isEmpty()) {
            request.setAttribute("mensaje", "El costo de contratación es obligatorio.");
            //System.err.println("El costo de contratación es obligatorio.");
            return false;
        } 
        if (costoAseguradoStr == null || costoAseguradoStr.trim().isEmpty()) {
            request.setAttribute("mensaje", "El costo asegurado es obligatorio.");
            //System.err.println("El costo asegurado es obligatorio.");
            return false;
        }
        try {
            float costoContratacion = Float.parseFloat(costoContratacionStr);
            float costoAsegurado = Float.parseFloat(costoAseguradoStr);

            if(costoContratacion < 0 || costoAsegurado < 0) {
                request.setAttribute("mensaje", "No se pueden ingresar números negativos.");
                return false;
            }

        } catch (NumberFormatException e) {
            request.setAttribute("mensaje", "El costo de contratación y el costo asegurado deben ser números válidos.");
            return false;
        }
        return true;
    }
}

	
