import './PropuestaCard.css'


function PropuestaCard({ pro}){
	
	
	
	
	
	return(
		
		<div className='divprincipalpropuesta'>
		
		
		<img 
		    className='imagen' 
		    style={{ width: '20vh', height: '20vh', objectFit: 'cover', borderRadius: '50%' }} 
		    src={pro.imagen} // <-- CORREGIDO: Usá pro.imagen para que coincida con tu objeto
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
		
					
					<button> Aceptar </button>		
					<button> Rechazar </button>	
					
		</div>
		
		
	
	);
}



export default PropuestaCard