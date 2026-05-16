import { useNavigate } from "react-router";







function Recuperarpassword(){
	
	
	const navigate = useNavigate();
	
	
	const volver = () => {
		
		navigate('/')
		
	}

	
	return(
		
		<div>
		<h1> asdas </h1>
		<button  onClick={volver}> Volver </button>
		</div>
		
	)
	
}

export default Recuperarpassword