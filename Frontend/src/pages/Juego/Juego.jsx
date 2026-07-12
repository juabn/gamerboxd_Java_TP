import './Juego.css'
import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

function JuegoResenia(){
	const { id } = useParams(); 
	const [juego, setJuego] = useState(null);
	 
	useEffect(() => {
	    // Usas el id capturado para pedir los datos específicos a tu API
	    fetch(`http://localhost:8081/juego/${id}`)
	      .then(res => res.json())
	      .then(data => setJuego(data))
	      .catch(err => console.error(err));
	  }, [id]);
	  
	  console.log(juego);
	  
	  if (!juego) return <p>Cargando...</p>;

	  const resenias = [
	    { id: 1, usuario: "dsf gdfsg ", comentario: "Usdf gsdfg fsdg ", puntuacion: 5 },
	    { id: 2, usuario: "CasualPlayer", comentario: "Muy laasda sdas de.", puntuacion: 4 }
	  ];

	  return (
	    <div className="detalle-juego-container">
	      <header className="game-header">
	        <h1>{juego.name}</h1>
	        <img src={juego.background_image}/>
	        <p>{juego.description_raw}</p>
	        <span>Desarrolladores: {juego.developers}</span>
	      </header>
		  
	      <section className="reviews-section">
	        <h2>Reseñas de usuarios</h2>
	        {resenias.map(r => (
	          <div key={r.id} className="review-card">
	            <strong>{r.usuario}</strong>
	            <p>{r.comentario}</p>
	            <span>Puntaje: {r.puntuacion}/5</span>
	          </div>
	        ))}
	      </section>
	    </div>
	  );
}

export default JuegoResenia;