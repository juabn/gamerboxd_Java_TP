import './PropuestaCard.css'


function PropuestaCard({ pro}){
	
	
	const actualizarEstado = (nuevoEstado) => {
			let token = localStorage.getItem('token');
			
			
			fetch('http://localhost:8081/actualizarpropuesta', {
				method: 'POST', 
				headers: {
					'Content-Type': 'application/json',
					'Authorization': 'Bearer ' + token
				},
				body: JSON.stringify({
	            id_propuesta: pro.id,            
	            estado: nuevoEstado  ,
				nombrejuego: pro.name  
				        })
			})
			.then(response => {
				if (response.status === 200) {
					alert('Bien');
					window.location.reload(); 
					}
				else if (response.status === 404){
					
					alert("Este juego ya existe")
				}
				 else {
					alert("Hubo un problema al actualizar el estado: " + response.status);
				}
			})
			.catch(error => console.error('Error al actualizar:', error));
		};
	
	
	
	
	
	return(
		
		<div className='divprincipalpropuesta'>
		
		
		<img 
		    className='imagen' 
		    style={{ width: '20vh', height: '20vh', objectFit: 'cover', borderRadius: '50%' }} 
		    src={pro.imagen} 
		/>
		
		<p className='textopropuesta'> {pro.name}</p>
		<p className='textopropuesta'> {pro.descripcion}</p>
		<p className='textopropuesta'> {pro.estado}</p>
		
		<div className='contenedor-companias'>
		                {pro.companias && pro.companias.map((comp, index) => (
							<p key={comp.idcompania || index} className='textopropuesta'>
							{comp.nombre || comp.name}
							</p>
		                ))}
		            </div>
		
					
				<button onClick={() => actualizarEstado('aceptado')}> Aceptar </button>		
				<button onClick={() => actualizarEstado('rechazado')}> Rechazar </button>
					
		</div>
		
		
	
	);
}



export default PropuestaCard