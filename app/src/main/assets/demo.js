var player;
var isPlaying;
// Callback for when the YouTube iFrame player is ready
function onYouTubeIframeAPIReady() {
  player = new YT.Player('player', {
    // Set Player height and width
    height: '100%',
    width: '100%',
    // Set the id of the video to be played
    //videoId: 'M7lc1UVf-VE',
    videoId: '[VIDEO_ID]',
    // Setup event handelers
    events: {
      'onReady': onPlayerReady,
      'onError': onError,
      'onApiChange': onApiChange,
    }
  });
};

// Event Handlers 
function onError(error){
  // Update errors on page
  console.log("Error!");
};
function onApiChange(event){
  // Update currently availbe APIs
  console.log("API Change!");
};
function onPlayerReady(){
  // Update page after player is ready
  playVideo();
  isPlaying = true;
}

// Functions to invoke user requested action through the iFrame API
function loadNewVideo(vid){
  console.log('loadNewVideo:',vid);
  player.loadVideoById(vid);
};
function cueNewVideo(vid){
  player.cueVideoById(vid);
};
function playPauseVideo(){
  console.log('isplaying:',isPlaying);
    if (isPlaying) {
      pauseVideo();
    }else{
      playVideo();
    }
    isPlaying = !isPlaying;
};
function playVideo(){
  player.playVideo();
};
function pauseVideo(){
  player.pauseVideo();
};
function stopVideo(){
  player.stopVideo();
};
function fastForward(){
  if ((player.getCurrentTime() + 5) < player.getDuration()) {
    seekTo(player.getCurrentTime() + 5);
  }else{
    seekTo(player.getDuration());
  }
  //console.log('currentTime:', player.getCurrentTime(), 'duration:', player.getDuration());
};
function fastRewind(){
  if ((player.getCurrentTime() - 5) > 0) {
    seekTo(player.getCurrentTime() - 5);
  }else{
    seekTo(0);
  }
};
function seekTo(seek){
  player.seekTo(seek);  
};
function volumeDown(){
  if ((player.getVolume() - 10) < 1) {
    setVolume(1);
  }else{
    setVolume(player.getVolume() - 10);
  }
};
function volumeUp(){
  if ((player.getVolume() + 10) > 100) {
    setVolume(100);
  }else{
    setVolume(player.getVolume() + 10);
  }
};
function setVolume(volume){
  player.setVolume(volume);  
};
function mute(){
  player.mute();
};
function unmute(){
  player.unMute();  
};
/*function setQuality(){
  player.setPlaybackQuality(document.getElementById("qualityOption").value);  
};
function setRate(){
  player.setPlaybackRate(document.getElementById("rateOption").value);  
};*/