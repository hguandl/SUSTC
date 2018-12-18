// Initialize Builder Demo
//

$(function() {
  if (!$.support.transition) { return; }

  var curStage = -1;
  var _builderAnimationStarted = false;

  function nextStage() {
    var prevStage = curStage;

    if (curStage >= stages.length - 1) {
      curStage = 0;
    } else {
      curStage++;
    }

    $('#landing-demo-builder')
      .removeClass('stage' + prevStage)
      .addClass('stage' + curStage);

    stages[curStage]();
  }

  function moveCursorTo(x, y) {
    var dfr = $.Deferred();

    var pX = (x / 800) * 100;
    var pY = (y / 578) * 100;

    $('#demo-builder-cursor')[0].style.left = pX + '%';
    $('#demo-builder-cursor')[0].style.top = pY + '%';

    setTimeout(function() {
      dfr.resolve();
    }, 1000);

    return dfr;
  }

  function makeClick() {
    var dfr = $.Deferred();

    $('#demo-builder-cursor-blink').addClass('blink');

    setTimeout(function() {
      $('#demo-builder-cursor-blink').removeClass('blink');
      dfr.resolve();
    }, 1000);

    return dfr;
  }

  function wait(delay) {
    var dfr = $.Deferred();

    setTimeout(function() {
      dfr.resolve();
    }, delay || 0);

    return dfr;
  }

  // Stages

  var stages = [
    function() {
      wait(1000)
        .then(function() { return moveCursorTo(534, 251); })
        .then(function() { return makeClick(); })
        .then(function() { nextStage(); });
    },
    function() {
      moveCursorTo(545, 552)
        .then(function() { return makeClick(); })
        .then(function() { nextStage(); });
    },
    function() {
      moveCursorTo(634, 120)
        .then(function() { return makeClick(); })
        .then(function() { return moveCursorTo(528, 172); })
        .then(function() { return makeClick(); })
        .then(function() { return moveCursorTo(377, 281); })
        .then(function() { return makeClick(); })
        .then(function() { return moveCursorTo(660, 381); })
        .then(function() { return makeClick(); })
        .then(function() { return moveCursorTo(522, 550); })
        .then(function() { return makeClick(); })
        .then(function() { nextStage(); });
    },
    function() {
      moveCursorTo(275, 177)
        .then(function() { return makeClick(); })
        .then(function() { return moveCursorTo(313, 316); })
        .then(function() { return makeClick(); })
        .then(function() { return moveCursorTo(575, 316); })
        .then(function() { return makeClick(); })
        .then(function() { return moveCursorTo(522, 550); })
        .then(function() { return makeClick(); })
        .then(function() { nextStage(); });
    },
    function() {
      moveCursorTo(532, 291)
        .then(function() { return makeClick(); })
        .then(function() { nextStage(); });
    },
    function() {
      wait(5000)
        .then(function() { nextStage(); });
    },
    function() {
      // Empty stage to pause animation
    },
    function() {
      moveCursorTo(675, 272)
        .then(function() { return makeClick(); })
        .then(function() { nextStage(); });
    },
  ];

  // Repeat button
  $('#demo-builder-repeat').on('click', function(e) {
    e.preventDefault();
    nextStage();
  });

  // Start
  //

  var windowLoaded = false;
  var framesLoaded = false;

  $('#demo-builder-window').on('load', function() { windowLoaded = true; });
  $('#demo-builder-frames').on('load', function() { framesLoaded = true; });

  // Ensure that images aren't already loaded
  windowLoaded = windowLoaded ? windowLoaded : $('#demo-builder-window').hasClass('lazyloaded');
  framesLoaded = framesLoaded ? framesLoaded : $('#demo-builder-frames').hasClass('lazyloaded');

  function waitForImagesLoading(cb) {
    if (windowLoaded && framesLoaded) { return cb(); }

    setTimeout(waitForImagesLoading.bind(null, cb), 200);
  }

  // Add animation trigger
  $('#landing-demo-builder').waypoint({
    handler: function() {
      waitForImagesLoading(function() {
        if (_builderAnimationStarted) { return; }
        $('#demo-builder-cursor').show();
        nextStage();
        _builderAnimationStarted = true;
      });
    },
    offset: '25%',
  });

});
