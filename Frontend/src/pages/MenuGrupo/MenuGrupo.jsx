import './MenuGrupo.css'
import { useNavigate } from "react-router";


function MenuGrupo({ onNavigate }){
	
	const navigate = useNavigate();
	
	const redirigiraGrupos = () =>{
		
		navigate("/ListadoGrupos")
		onNavigate?.();
		
	}
	
	const redirigiraCrearGrupos = () =>{
		
		navigate("/crearGrupo")
		onNavigate?.();
		
	}
	
	
	
	
	
	return (
		<div className='divprincipalmenugrupo'>
		<button className='menugrupo-item' onClick={redirigiraCrearGrupos}> Crear grupo </button>
		<button className='menugrupo-item' onClick={redirigiraGrupos}> Ver grupos existentes </button>
		</div>
	)
}

export default MenuGrupo
