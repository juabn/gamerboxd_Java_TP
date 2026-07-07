
import './reviewLandCard.css';

export default function ReviewLandCard({ fotoJuego, tituloResenia, puntaje }) {
  return (
    <div className="card-container">
      {fotoJuego && (
        <div className="card-image-wrapper">
          <img src={fotoJuego} alt="Portada del juego" className="card-image" />
        </div>
      )}
      <div className="card-content">
        <h3 className="card-title">{tituloResenia}</h3>
        <p className="card-description">
          Puntaje: <strong>{puntaje} / 5</strong>
        </p>
      </div>
    </div>
  );
}
