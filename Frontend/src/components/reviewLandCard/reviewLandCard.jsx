import './reviewLandCard.css';

export default function ReviewLandCard({ 
  fotoJuego, 
  titulo, 
  puntaje,
  descripcion,
  nombreJuego, 
  nombreUsuario          
}) {
  return (
    <div 
      className="card-container-moderna"
      style={{ 
        backgroundImage: `url(${fotoJuego}')` 
      }}
    >
      <div className="card-overlay">
        
        
  		<div className="card-top-info">
        <h2 className="card-title-moderna">
          <span className="comillas">"</span> {titulo} <span className="comillas">"</span>
        </h2>
        
        <p className="card-descripcion">{descripcion}</p>
      </div>
        
        <div className="card-bottom-info">
          <h4 className="card-game-name">{nombreJuego}</h4>
          <p className="card-score-moderna">⭐ {puntaje} / 5</p>
          <p className="card-user-name">Por: @{nombreUsuario}</p>
        </div>

      </div>
    </div>
  );
}