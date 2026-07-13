import './Juego.css'
import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

function JuegoResenia(){
	
	const token = localStorage.getItem('token');
	const { id } = useParams(); 
	const [juego, setJuego] = useState(null);
	const [resenias, setResenias] = useState([]);
	 
	useEffect(() => {
	    
	    fetch(`http://localhost:8081/juego/${id}`)
			
	      .then(res => res.json())
	      .then(data => setJuego(data))
	      .catch(err => console.error(err));
	  }, [id]);
	  
	  useEffect(() => {
		
		fetch(`http://localhost:8081/reseniasPorJuego?id=${id}`)
		/*.then(res=>{
						if(!res.ok){
							return res.text().then(text=>{throw new Error(text)});
						}
					})*/	
		  .then(res => res.json())
		  .then(data => setResenias(data))
		  .catch(err => console.error(err));
		},[id]);
	  
	  console.log(juego);
	  
	  if (!juego) return <p>Cargando...</p>;

	  

	  return (
	    <div className="detalle-juego-container">
	      <header className="game-header">
		  	<div className="gameInfo">
				<div className="gameInfo1">
			        <h1>{juego.name}</h1>
			        <p>{juego.description_raw}</p>
				</div>
		        <span>Desarrolladores: {juego.developers}</span>
			</div>
			<div className="game-pic">
				<img src={juego.background_image}/>
			</div>
		
		
	      </header>
		  
	      <section className="reviews-section">
	        <h2>Reseñas de usuarios</h2>
	        {resenias.map((r,index) => (
		        <div key={index} className="review-card">
					<div className="reviewUserInfo">
					<img 
				          src={`https://ui-avatars.com/api/?name=${r.mail_usuario}&background=random&color=fff`} 
				          className="user-avatar" 
				        />
				  <strong>{r.mail_usuario}</strong>
				</div>
	            <p>{r.descripcion}</p>
	            <span>Puntaje: {r.puntaje}/5</span>
	          </div>
	        ))}
	      </section>
	    </div>
	  );
}

export default JuegoResenia;