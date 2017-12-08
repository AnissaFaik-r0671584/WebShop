package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Person;
import domain.Product;
import domain.ShopService;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShopService shopService = new ShopService();

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String destination = "index.jsp";

		if (action == null) {
			action = "";
		}

		switch (action) {
		case "overview":
			destination = getPersonOverview(request, response);
			break;
		case "signUp":
			destination = signUp(request, response);
			break;
		case "signUpConfirmed":
			destination = signUpConfirmed(request, response);
			break;
		case "deletePerson":
			destination = deletePerson(request, response);
			break;
		case "deletePersonConfirmed":
			destination = deletePersonConfirmed(request, response);
			break;
		case "updatePerson":
			destination = updatePerson(request, response);
			break;
		case "updatePersonConfirmed":
			destination = updatePersonConfirmed(request, response);
			break;
		case "products":
			destination = getProducts(request, response);
			break;
		case "addProduct":
			destination = addProduct(request, response);
			break;
		case "addProductConfirmed":
			destination = addProductConfirmed(request, response);
			break;
		case "deleteProduct":
			destination = deleteProduct(request, response);
			break;
		case "deleteProductConfirmed":
			destination = deleteProductConfirmed(request, response);
			break;
		case "updateProduct":
			destination = updateProduct(request, response);
			break;
		case "updateProductConfirmed":
			destination = updateProductConfirmed(request, response);
			break;
		default:
			destination = "index.jsp";
			break;
		}

		RequestDispatcher view = request.getRequestDispatcher(destination);
		view.forward(request, response);

	}

	//PERSON HANDLER METHODS
	private String getPersonOverview(HttpServletRequest request, HttpServletResponse response) {
		List<Person> personList = (ArrayList<Person>) shopService.getPersons();
		request.setAttribute("personList", personList);
		return "personoverview.jsp";
	}

	private String signUp(HttpServletRequest request, HttpServletResponse response) {

		return "signUp.jsp";
	}

	private String signUpConfirmed(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<String> errors = new ArrayList<String>();
		Person person = new Person();
		setEmail(person, errors, request);
		setUserId(person, errors, request);
		setFirstName(person, errors, request);
		setLastName(person, errors, request);
		setPassword(person, errors, request);
		setUserId(person, errors, request);

		if (errors.size() == 0) {
			try {
				shopService.addPerson(person);
				return "index.jsp";
			} catch (Exception e) {
				errors.add(e.getMessage());
			}
		}
		request.setAttribute("errors", errors);
		request.setAttribute("person", person);
		return "signUp.jsp";
	}

	private String updatePerson(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		Person person = shopService.getPerson(userId);
		request.setAttribute("person", person);
		return "updatePerson.jsp";
	}

	private String updatePersonConfirmed(HttpServletRequest request, HttpServletResponse response) {
		Person person = new Person();
		ArrayList<String> errors = new ArrayList<String>();
		setFirstName(person, errors, request);
		setLastName(person, errors, request);
		setEmail(person, errors, request);
		setPassword(person, errors, request);
		setUserId(person, errors, request);

		if (errors.size() == 0) {
			try {
				shopService.updatePersons(person);
				return getPersonOverview(request, response);
			} catch (Exception e) {
				errors.add(e.getMessage());
			}
		}
		request.setAttribute("errors", errors);
		request.setAttribute("personToUpdate", person);
		return "updatePerson.jsp";
	}

	private String deletePerson(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userid");
		if (userId == null || userId.isEmpty()) {
			return getPersonOverview(request, response);
		}
		request.setAttribute("personToDelete", shopService.getPerson(userId));
		return "deletePerson.jsp";
	}

	private String deletePersonConfirmed(HttpServletRequest request, HttpServletResponse response) {
		String buttonValue = request.getParameter("button");
		if (buttonValue.equals("Sure")) {
			shopService.deletePerson(request.getParameter("userId"));
		}
		return getPersonOverview(request, response);
	}

	//PRODUCT HANDLER METHODS
	private String getProducts(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Product> products = (ArrayList<Product>) shopService.getProducts();
		request.setAttribute("products", products);
		return "productOverview.jsp";
	}
	
	private String addProduct(HttpServletRequest request, HttpServletResponse response) {
		return "addProduct.jsp";
	}

	private String addProductConfirmed(HttpServletRequest request, HttpServletResponse response) {
		Product product = new Product();
		ArrayList<String> errors = new ArrayList<String>();
		setProductName(request, response, errors, product);
		setProductDescription(request, response, errors, product);
		setProductPrice(request, response, errors, product);

		if (errors.size() == 0) {
			try {
				shopService.addProduct(product);
				return getProducts(request, response);
			} catch (Exception e) {
				errors.add(e.getMessage());
			}
		}
		request.setAttribute("errors", errors);
		request.setAttribute("product", product);
		return "addProduct.jsp";
	}
	
	private String updateProduct(HttpServletRequest request, HttpServletResponse response) {
		int productId = Integer.parseInt(request.getParameter("productId"));
		request.setAttribute("productToUpdate", shopService.getProduct(productId));
		return "updateProduct.jsp";
	}
	
	private String updateProductConfirmed(HttpServletRequest request, HttpServletResponse response) {
		Product product = new Product();
		ArrayList<String> errors = new ArrayList<String>();
		setProductName(request, response, errors, product);
		setProductDescription(request, response, errors, product);
		setProductPrice(request, response, errors, product);
		setProductId(request, response, errors, product);

		if (errors.size() == 0) {
			try {
				shopService.updateProduct(product);
				return getProducts(request, response);
			} catch (Exception e) {
				errors.add(e.getMessage());
			}
		}
		request.setAttribute("errors", errors);
		request.setAttribute("productToUpdate", product);
		return "updateProduct.jsp";
	}

	private String deleteProduct(HttpServletRequest request, HttpServletResponse response) {
		int productId = Integer.parseInt(request.getParameter("productId"));
		if (productId == 0 ) {
			return getProducts(request, response);
		}
		request.setAttribute("productToDelete", shopService.getProduct(productId));
		return "deleteProduct.jsp";
	}

	private String deleteProductConfirmed(HttpServletRequest request, HttpServletResponse response) {
		String buttonValue = request.getParameter("button");
		if (buttonValue.equals("Sure")) {
			shopService.deleteProduct(Integer.parseInt(request.getParameter("productId")));
		}
		return getProducts(request, response);
	}

	//PERSON SET METHODS
	private void setPassword(Person person, ArrayList<String> errors, HttpServletRequest request) {
		String password = request.getParameter("password");
		try {
			person.setPassword(password);
		} catch (Exception e) {
			errors.add(e.getMessage());
		}

	}

	private void setLastName(Person person, ArrayList<String> errors, HttpServletRequest request) {
		String lastName = request.getParameter("lastName");
		try {
			person.setLastName(lastName);
		} catch (Exception e) {
			errors.add(e.getMessage());
		}

	}

	private void setFirstName(Person person, ArrayList<String> errors, HttpServletRequest request) {
		String firstName = request.getParameter("firstName");
		try {
			person.setFirstName(firstName);
		} catch (Exception e) {
			errors.add(e.getMessage());
		}

	}

	private void setUserId(Person person, ArrayList<String> errors, HttpServletRequest request) {
		String userId = request.getParameter("userid");
		try {
			person.setUserid(userId);
		} catch (Exception e) {
			errors.add(e.getMessage());
		}

	}

	private void setEmail(Person person, ArrayList<String> errors, HttpServletRequest request) {
		String email = request.getParameter("email");
		try {
			person.setEmail(email);
		} catch (Exception e) {
			errors.add(e.getMessage());
		}

	}

	//PRODUCT SET METHODS
	private void setProductPrice(HttpServletRequest request, HttpServletResponse response, ArrayList<String> errors,
			Product product) {
		try {
			product.setPrice(request.getParameter("price"));
		} catch (Exception e) {
			errors.add(e.getMessage());
		}

	}

	private void setProductDescription(HttpServletRequest request, HttpServletResponse response,
			ArrayList<String> errors, Product product) {
		request.setAttribute("description", request.getParameter("description"));
		try {
			product.setDescription(request.getParameter("description"));
		} catch (Exception e) {
			errors.add(e.getMessage());
		}
	}

	private void setProductName(HttpServletRequest request, HttpServletResponse response, ArrayList<String> errors,
			Product product) {
		try {
			product.setName(request.getParameter("name"));
		} catch (Exception e) {
			errors.add(e.getMessage());
		}

	}

	private void setProductId(HttpServletRequest request, HttpServletResponse response, ArrayList<String> errors,
			Product product) {
		try {
			product.setProductId(Integer.parseInt(request.getParameter("productId")));
		} catch (Exception e) {
			errors.add(e.getMessage());
		}
	}
}
