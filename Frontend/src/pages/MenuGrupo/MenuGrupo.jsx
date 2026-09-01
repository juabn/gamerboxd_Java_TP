import './MenuGrupo.css'
import { useNavigate } from "react-router";


function MenuGrupo(){
	
	const navigate = useNavigate();
	
	const redirigiraGrupos = () =>{
		
		navigate("/ListadoGrupos")
		
		
	}
	
	const redirigiraCrearGrupos = () =>{
		
		navigate("/crearGrupo")
		
		
	}
	
	
	
	
	
	return (
		<div className='divprincipalmenugrupo'>
		<button onClick={redirigiraCrearGrupos}> Crear grupo </button>
		<button onClick={redirigiraGrupos}> Ver grupos existentes </button>
		</div>
	)
}

export default MenuGrupo