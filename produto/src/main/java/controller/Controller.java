package controller;
 
import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO; // classe do banco de dados
import model.JavaBeans;
 
 
// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
@WebServlet(urlPatterns = {"/main", "/insert", "/select","/update","/delete","/report"})
 
 
public class Controller extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
 
	/** The dao. */
	DAO dao = new DAO(); 
	
	/** The produto. */
	JavaBeans produto = new JavaBeans();
 
    
 
    /**
     * Instantiates a new controller.
     */
    public Controller() {
        super();
        
    }
 
	
 
	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String action = request.getServletPath();
		System.out.println(action);
 
		if(action.equals("/main")) {
			produtos(request, response);
		}else if(action.equals("/insert")){ 
			adicionarProduto(request,response);
		}else if(action.equals("/select")){
			listarProduto(request, response);
		}else if(action.equals("/update")){
			editarProduto(request, response);
		}else if(action.equals("/delete")){
			removerProduto(request, response);
		}else if(action.equals("/report")){
			gerarRelatorio(request, response);
		}else {
			response.sendRedirect("index.html");
		}
	}
 
	/**
	 * Produtos.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void produtos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<JavaBeans> lista = dao.listarProdutos();
		
		request.setAttribute("produtos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("cadastro.jsp");
		rd.forward(request, response);
		 
	}
 
	
 
	/**
	 * Adicionar produto.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void adicionarProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
 
		
 
		
 
		int qtde = Integer.parseInt(request.getParameter("qtde"));
		String valorUnitStr = request.getParameter("valorunit").replace(",",".");
		float valorUnit = Float.parseFloat(valorUnitStr);
		String dataTexto = request.getParameter("data_cad");
		LocalDate data = LocalDate.parse(dataTexto);
		produto.setNome(request.getParameter("nome_produto"));
		produto.setQtde(qtde);
		produto.setValor_unit(valorUnit);
		produto.setData_cad(data);
		dao.inserirProduto(produto);
		
		response.sendRedirect("main");
	}
 
	/**
	 * Listar produto.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void listarProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		System.out.println(id);
		
		produto.setId(id);
		dao.selecionarProduto(produto);
 
		
		NumberFormat formato = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));
		String valorFormatado = formato.format(produto.getValor_unit());
		
		
		
		request.setAttribute("id", produto.getId());
		request.setAttribute("nome", produto.getNome());
		request.setAttribute("qtde", produto.getQtde());
		request.setAttribute("valorunit", valorFormatado); 
		request.setAttribute("data_cadastro", produto.getData_cad());
		
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request,response);
	}
	
	/**
	 * Editar produto.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void editarProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			
			int id = Integer.parseInt(request.getParameter("id"));
			String nome = request.getParameter("nome_produto");
			int qtde = Integer.parseInt(request.getParameter("qtde"));
			
			String valorTexto = request.getParameter("valorunit");
			valorTexto = valorTexto.replaceAll("[\\s\\u00A0]","");
			
			valorTexto = valorTexto.replace("R$","");
			
			valorTexto = valorTexto.replace(",",".");
			
			float valorUnit = Float.parseFloat(valorTexto);
			
			
			LocalDate datacadastro = LocalDate.parse(request.getParameter("data_cad"));
			
			produto.setId(id);
			produto.setNome(nome);
			produto.setQtde(qtde);
			produto.setValor_unit(valorUnit);
			produto.setData_cad(datacadastro);
			
			
			dao.alterarProduto(produto);
			
			
			
		} catch (Exception e) {
			
			System.err.println("Erro ao editar produto:" + e.getMessage());
			
		}
		
		response.sendRedirect("main");
	}
	
	/**
	 * Remover produto.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void removerProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id =  Integer.parseInt(request.getParameter("id"));
		
		
		produto.setId(id);
		
		
		dao.deletarProduto(produto);
		
		
		response.sendRedirect("main");
	}
		
	
	/**
	 * Gerar relatorio.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Document documento = new Document();
		
		try {
			
			response.setContentType("aplicativo/pdf");
			response.addHeader("Content-Disposition", "inline; filename=" + "Produto.pdf");
			
			
			PdfWriter.getInstance(documento, response.getOutputStream());
			
			
			documento.open();
			documento.add(new Paragraph("Lista de Produtos"));
			documento.add(new Paragraph(" "));
			
			
			PdfPTable tabela = new PdfPTable(4);
			
			
			PdfPCell col1 = new PdfPCell (new Paragraph("Nome Produto"));
			PdfPCell col2 = new PdfPCell (new Paragraph("Qtde"));
			PdfPCell col3 = new PdfPCell (new Paragraph("Valor Unitário"));
			PdfPCell col4 = new PdfPCell (new Paragraph("Data Cadastro"));
			
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			
			DateTimeFormatter formatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			NumberFormat moedaBR = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));
			
			
			ArrayList<JavaBeans> lista = dao.listarProdutos();
			for(int i = 0; i < lista.size(); i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(String.valueOf(lista.get(i).getQtde()));
				tabela.addCell(moedaBR.format(lista.get(i).getValor_unit()));
				String dataformatada = lista.get(i).getData_cad().format(formatada);
				tabela.addCell(dataformatada);
		
				
			}
			
			documento.add(tabela);
			documento.close();
			
			
			
		} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
	}	
}
 