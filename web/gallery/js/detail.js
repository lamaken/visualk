function handleDragStart(e) {
  alert(e.toString());
}

var photo = document.getElementById("cssImages");
photo.addEventListener('dragstart', handleDragStart, false);

