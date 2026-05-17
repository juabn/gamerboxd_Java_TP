import './ingresoToken.css'
import { useNavigate } from "react-router";

function IngresarToken(){
	
	const navigate = useNavigate();



	const volver = () => {
		
		navigate('/')
	}
		
	
	return(
		
		
		<div className="contendorprincipal">
		<h1> Ingresar Token </h1>
		<input placeholder="Ingrese el token" />
		<button> Enviar </button>
		<button onClick={volver}> Volver </button> 
		</div>
	)
	
	
		

		
	}
	
	

	



export default IngresarToken