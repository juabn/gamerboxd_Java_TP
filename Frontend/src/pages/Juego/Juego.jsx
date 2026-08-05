import './Juego.css'
import { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import FooterC from '../../components/Footer/Footer'

function JuegoResenia(){

	const { id } = useParams(); 
	const [juego, setJuego] = useState(null);
	const [resenias, setResenias] = useState([]);
	const [usuario, setUsuario] = useState(null);
	const [notificacion, setNotificacion] = useState(null); 
	const [usuarioLogueado, setUsuarioLogueado] = useState(()=>{
		const token = localStorage.getItem('token');
		return token ? token : null
	});
	const mostrarNotificacion = (tipo, texto) => {
	  setNotificacion({ tipo, texto });
	  setTimeout(() => {
	    setNotificacion(null);
	  }, 4000); 
	};
	const [reseniaPropia, setReseniaPropia] = useState(null);
	const [editando, setEditando] = useState(false); 

	const handleBorrarResenia = () => {
	    const confirmacion = window.confirm("esstas seguro de que deseas eliminar esta reseña?");
	    
	    if (confirmacion) {
			
			const token = localStorage.getItem('token'); 
	        fetch(`http://localhost:8081/borrarResenia?id=${id}`, {
	            method: 'DELETE',
				headers: {
					        'Content-Type': 'application/json',
					        'Authorization': `Bearer ${token}`
					      }
	        })
	        .then(response => {
	            if (response.ok) {
	                alert("Reseña borrada con éxito.");
	                
	            } else {
	                alert("Hubo un error al borrar la reseña.");
	            }
	        })
	        .catch(error => console.error("Error en la petición:", error));
	    }
	};
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

	    const data = await response.json();
	    return data;
	  } catch (error) {
	    console.error('Error:', error);
	    return null;
	  }
	};

	useEffect(() => {
	    fetch(`http://localhost:8081/juego/${id}`)
	      .then(res => res.json())
	      .then(data => setJuego(data))
	      .catch(err => console.error(err));
	  }, [id]);

	  const cargarResenias = () => {
		fetch(`http://localhost:8081/reseniasPorJuego?id=${id}`)
		  .then(res => res.json())
		  .then(data => setResenias(data))
		  .catch(err => console.error(err));
	  };

	  useEffect(() => {
		cargarResenias();
	  }, [id]);

	  useEffect(() => {
	  	if (usuarioLogueado) {
	  		obtenerUsuario().then(data => setUsuario(data));
	  	}
	  }, [usuarioLogueado]);

	  useEffect(() => {
	  	if (usuario && resenias.length > 0) {
	  		const propia = resenias.find(
	  			r => r.usuario.mail === usuario.mail
	  		);
	  		setReseniaPropia(propia || null);
	  	} else {
	  		setReseniaPropia(null);
	  	}
	  }, [usuario, resenias]);

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
			  	    })
			  		.then(res => {
			  		        if (!res.ok) {
			  		            return res.text().then(text => { throw new Error(text) });
			  		        }
			  		        return res.text();
			  		    })
			  		    .then(mensaje => {
			  		        mostrarNotificacion('exito', 'Reseña publicada correctamente');
			  				e.target.reset();
			  				cargarResenias();
			  		    })
			  		    .catch(err => {
			  		        mostrarNotificacion('error', 'No se pudo publicar la reseña');
			  		    });
	      };

	  const manejarEdicionResenia = (e) => {
	  		e.preventDefault();
	  		const formData = new FormData(e.target);
	  		const token = localStorage.getItem('token');
	  		const datosResenia = {
	  			id_juego: id,
	  			titulo: formData.get('titulo'),
	  			puntaje: parseFloat(formData.get('puntaje')),
	  			descripcion: formData.get('descripcion')
	  		}
	  		fetch('http://localhost:8081/editarResenia', {
	  			method: 'PUT',
	  			headers: {
	  				'Content-Type': 'application/json',
	  				'Authorization': `Bearer ${token}`
	  			},
	  			body: JSON.stringify(datosResenia)
	  		})
	  			.then(res => {
	  				if (!res.ok) {
	  					return res.text().then(text => { throw new Error(text) });
	  				}
	  				return res.text();
	  			})
	  			.then(mensaje => {
	  				mostrarNotificacion('exito', 'Reseña editada correctamente');
	  				setEditando(false); 
	  				cargarResenias();
	  			})
	  			.catch(err => {
	  				mostrarNotificacion('error', 'No se pudo editar la resenia');
	  			});
	  };

	  if (!juego) return <p>Cargando...</p>;


	  return (
		<section className="paginaJuegoDetalle">
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
			  {notificacion && (
			      <div className={`toast toast-${notificacion.tipo}`}>
			        {notificacion.tipo === 'exito' ? '✓' : '✕'} {notificacion.texto}
			      </div>
			    )}

		        <h2>Reseñas de usuarios</h2>
	
				
				{!usuarioLogueado && (
				    <div className="mensaje-login-requerido">
				        <p>Necesitas loguearte para reseniar</p>
				        <Link to="/login" className="btn-login">
				            Login
				        </Link>
				    </div>
				)}
	
				{usuarioLogueado && !reseniaPropia && (
				    <div className="review-card">
				        <form onSubmit={manejarEnvioResenia} className="formulario-resenia">
				            <div className="form-group">
				                <label>Titulo</label>
				                <input type="text" name="titulo" required />
				            </div>
	
				            <div className="form-group">
				                <label>Puntaje (1-5)</label>
				                <input type="number" name="puntaje" min="1" max="5" required />
				            </div>
	
				            <div className="form-group">
				                <label>Dinos por que</label>
				                <textarea name="descripcion" required></textarea>
				            </div>
	
				            <button type="submit" className="btn-primario">Enviar</button>
				        </form>
				    </div>
				)}
	
		        {resenias.map((r,index) => {
					console.log('usuario:', usuario);
					console.log('resenias:', resenias);
		        	const esPropia = usuario && r.usuario?.mail === usuario.mail;
	
		        	
		        	if (esPropia && editando) {
		        		return (
		        			<div key={index} className="review-card review-card-propia">
		        				<form onSubmit={manejarEdicionResenia} className="formulario-resenia">
		        					<div className="form-group">
		        						<label>Titulo</label>
		        						<input type="text" name="titulo" defaultValue={r.titulo} required />
		        					</div>
	
		        					<div className="form-group">
		        						<label>Puntaje (1-5)</label>
		        						<input type="number" name="puntaje" min="1" max="5" defaultValue={r.puntaje} required />
		        					</div>
	
		        					<div className="form-group">
		        						<label>Dinos por que</label>
		        						<textarea name="descripcion" defaultValue={r.descripcion} required></textarea>
		        					</div>
	
		        					<div className="acciones-form">
		        						<button type="submit" className="btn-primario">Guardar cambios</button>
		        						<button type="button" className="btn-secundario" onClick={() => setEditando(false)}>Cancelar</button>
		        					</div>
		        				</form>
		        			</div>
		        		);
		        	}
	
		        	
		        	return (
				        <div key={index} className={`review-card ${esPropia ? 'review-card-propia' : ''}`}>
							<div className="review-card-header">
								<div className="reviewUserInfo">
									<img 
								          src={r.usuario.foto_perfil || `https://i.pravatar.cc/150?img=3`} 
								          className="user-avatar" 
								        />
								  <strong>{r.usuario.nombre_usuario}</strong>
								</div>
	
								{esPropia && (
									<div className="botones-accion-resenia">
									<button className="btn-editar" onClick={() => setEditando(true)}>
										Editar
									</button>
									<button 
									        className="btn-primary btn-danger" 
									        onClick={() => handleBorrarResenia()}
									    >
									        Borrar
									    </button>
									</div>
								)}
							</div>
			            <p>{r.descripcion}</p>
			            <span className="puntaje-badge">Puntaje: {r.puntaje}/5</span>
			          </div>
			        );
		        })}
		      </section>
			  </div>
			  
			  <section className="juegoDetalleFooter">
			  	<FooterC/>
			  </section>
		</section>
	    
	  );
}

export default JuegoResenia;