### Library for display typing chat member indicator

##### Setup of View
-----------
Just add to your XML class TypingAnimationView.
Configure programmatically fields
```kotlin
@DrawableRes
var imageCircle: Int = R.drawable.image_circle_typing
var circleCount = DEFAULT_CIRCLES_COUNT
var circleAnimDuration = DEFAULT_CIRCLE_DURATION
var startCircleAnimDuration = DEFAULT_START_ANIM_CIRCLE_DURATION
var scaleStep = DEFAULT_ANIM_SCALE_STEP
var stepBetweenCircleDp = DEFAULT_MARGIN_CIRCLES.dpToPx(resources)
var autoStartByAttachToWindow = true
```  
And use it.

Has methods.
```kotlin
fun startCircleAnimation()
fun stopCircleAnimation()
fun setCircles()
```


#### Preview
![](https://i.ibb.co/QfFBc0Y/2019-04-02-10-20-05.jpg)