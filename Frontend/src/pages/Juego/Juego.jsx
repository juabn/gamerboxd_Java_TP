import './Juego.css'
import { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';

function JuegoResenia(){
	
	
	const { id } = useParams(); 
	const [juego, setJuego] = useState(null);
	const [resenias, setResenias] = useState([]);
	const [usuarioLogueado, setUsuarioLogueado] = useState(()=>{
		const token = localStorage.getItem('token');
		return token ? token : null
	});
	const [reseniaPropia, setReseniaPropia] = useState(null);
	
	

	
	const obtenerUsuario = async () => {
	  const token = localStorage.getItem('token'); 

	  try {
	    const response = await fetch('http://localhost:8081/obtenerUsuarioToken', {
	      method: 'GET',
	      headers: {
	        'Content-Type': 'application/json',
	        'Authorization': `Bearer ${token}`
	      }
	    });

	    if (!response.ok) {
	      throw new Error('error al obtener usuario');
	    }

	    const usuario = await response.json();
	    return usuario;
	  } catch (error) {
	    console.error('Error:', error);
	  }
	};
	
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
		
	
		
		
	  const manejarEnvioResenia = (e) => {
	          e.preventDefault();
			  const formData = new FormData(e.target);
			  const token = localStorage.getItem('token');
			  const datosResenia = {
			  		id_juego: id,
			  		titulo: formData.get('titulo'),
			  		puntaje:parseFloat(formData.get('puntaje')),
			  		descripcion:formData.get('descripcion')
			  	}
			  	fetch('http://localhost:8081/nuevaResenia', {
			  	        method: 'POST',
			  	        headers: {
			  	            'Content-Type': 'application/json',
			  	            'Authorization': `Bearer ${token}` 
			  	        },
			  	        body: JSON.stringify(datosResenia)
			  	    }) 		.then(res => {
			  		        if (!res.ok) {
			  		            return res.text().then(text => { throw new Error(text) });
			  		        }
			  		        return res.text();
			  		    })
			  		    .then(mensaje => {
			  		        alert("resenia publicada");
			  				console.log(mensaje);
			  		        event.target.reset(); 
			  		    })
			  		    .catch(err => {
			  		        alert("error: " + err.message); 
			  		    });
	          
	      };
	  
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
			<div className="seccion-nueva-resenia">
			                <hr />
			                

			                {usuarioLogueado ? (
			                    
			                    <form onSubmit={manejarEnvioResenia} className="formulario-resenia">
			                        <div>
			                            <label>Titulo:</label>
			                            <input type="text" name="titulo" required />
			                        </div>
			                        
			                        <div>
			                            <label>Puntaje (1-5):</label>
			                            <input type="number" name="puntaje" min="1" max="5" required />
			                        </div>

			                        <div>
			                            <label>Dinos por que:</label>
			                            <textarea name="descripcion" required></textarea>
			                        </div>

			                        <button type="submit">Enviar</button>
			                    </form>
			                ) : (
			                    
			                    <div className="mensaje-login-requerido">
			                        <p>Necesitas loguearte para reseniar</p>
			                        <Link to="/login" className="btn-login">
			                            Login
			                        </Link>
			                    </div>
			                )}
			            </div>
	        {resenias.map((r,index) => (
		        <div key={index} className="review-card">
					<div className="reviewUserInfo">
					<img 
				          src={r.usuario.foto_perfil || `https://i.pravatar.cc/150?img=3`} 
				          className="user-avatar" 
				        />
				  <strong>{r.usuario.nombre_usuario}</strong>
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