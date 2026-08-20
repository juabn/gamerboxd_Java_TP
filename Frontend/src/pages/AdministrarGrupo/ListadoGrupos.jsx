import './ListadoGrupos.css'
import GruposCard from '../../components/GruposCard/GruposCard';
import { useState, useEffect } from 'react';

function Propuestas(){
	
	const [listaGrupos, setListaGrupos] = useState([]);
	
	
	useEffect(() => {
	let token = localStorage.getItem('token');
	
	fetch('http://localhost:8081/listarGrupos',{
					
				method: 'POST', 
				headers: {
				'Authorization': 'Bearer ' + token },			
		})
	.then(response => response.json())
	.then(data => {
		
		setListaGrupos(data);
		console.log(data)
	})
	.catch(error => console.error('Error:', error));

	}, []);
	
	return(
			<div className='divprincipallistadogrupos '>
				
				<h2> Grupos existentes </h2>
				
	
				{listaGrupos.map((grupo) => (
					<GruposCard
						key={grupo.id} 
						gru={{
							id: grupo.id,
							name: grupo.nombre,
							descripcion: grupo.descripcion,
							imagen: grupo.foto_perfil
							
							
						}} 
					/>
				))}
				
			</div>
		);
	
	
	
}


export default Propuestas