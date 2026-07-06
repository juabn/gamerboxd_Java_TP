
import './reviewLandCard.css';

const reviewLandCard = ({ title, description, imageUrl, buttonText }) => {
  return (
    <div className="card-container">
      {imageUrl && (
        <div className="card-image-wrapper">
          <img src={imageUrl} alt={title} className="card-image" />
        </div>
      )}
      <div className="card-content">
        <h3 className="card-title">{title}</h3>
        <p className="card-description">{description}</p>
        {buttonText && (
          <button className="card-button">{buttonText}</button>
        )}
      </div>
    </div>
  );
};

export default reviewLandCard;