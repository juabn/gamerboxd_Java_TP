import './Propuestas.css'
import PropuestaCard from '../../components/PropuestaCard/PropuestaCard';
import { useState, useEffect } from 'react';

function Propuestas(){
	
	const [listaPropuestas, setListaPropuestas] = useState([]);
	
	
	useEffect(() => {
	let token = localStorage.getItem('token');
	
	fetch('http://localhost:8081/listarPropuestas',{
					
					
				method: 'POST', 
				headers: {
				'Authorization': 'Bearer ' + token },			
		})
	.then(response => response.json())
	.then(data => {
		
		setListaPropuestas(data);
		console.log(data)
	})
	.catch(error => console.error('Error:', error));

	}, []);
	
	return(
			<div className='divprincipalpropuesta'>
				
				<h2> Propuestas Enviadas </h2>
				
	
				{listaPropuestas.map((propuesta) => (
					<PropuestaCard 
						key={propuesta.idPropuesta} 
						pro={{
							name: propuesta.nombrejuego,
							descripcion: propuesta.descripcionjuego,
							imagen: propuesta.foto,
							estado: propuesta.estado,
							companias: propuesta.companiasJuego
							
						}} 
					/>
				))}
				
			</div>
		);
	
	
	
}


export default Propuestas