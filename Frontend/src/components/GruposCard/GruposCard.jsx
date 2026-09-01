import './GruposCard.css'
import { useNavigate } from "react-router";
import { API_URL } from '../../config';


function GruposCard({ gru}){
	
	const navigate = useNavigate();
	
	
	const actualizarEstado = () => {
			let token = localStorage.getItem('token');
			
			
			fetch(`${API_URL}/aniadirMiembroAGrupo`, {
				method: 'POST', 
				headers: {
					'Content-Type': 'application/json',
					'Authorization': 'Bearer ' + token
				},
				body: JSON.stringify({
				id: gru.id
	            
				        })
			})
			.then(response => {
				if (response.status === 200) {
					console.log("bien")
					alert('Fuiste aniadido al grupo');
					navigate('/');
					}
				 else {
					alert("Hubo un problema al agregarlo al grupo: " + response.status);
				}
			})
			.catch(error => console.error('Error al actualizar:', error));
		};
	
	
	
	
	
	return(
		
		<div className='divprincipalpropuesta'>
		
		
		<img 
		    className='imagen' 
		    style={{ width: '20vh', height: '20vh', objectFit: 'cover', borderRadius: '50%' }} 
		    src={gru.imagen} 
		/>
		
		<p className='textopropuesta'> {gru.name}</p>
		<p className='textopropuesta'> {gru.descripcion}</p>
		
	
		<button onClick={() => actualizarEstado('aceptado')}> Entrar </button>		
		
					
		</div>
		
		
	
	);
}



export default GruposCard