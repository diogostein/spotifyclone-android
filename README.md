# Spotify Clone

<div>
  <img height="600" src="https://diogostein.dev/assets/codelabs/spotify_1-ffdd66d96adcc252b2d47a7355f39eff24b484d9dae90997fe84a2028ec24970.png" />
  <img height="600" src="https://diogostein.dev/assets/codelabs/spotify_2-209b8bf4c5b00d193cdb18345fca8be28f249e2cd82b92fc2cf3d9255937142d.png" />
  <img height="600" src="https://diogostein.dev/assets/codelabs/spotify_3-17086b7ab0cb5d44a1a4971e58ca55f03b63027c0a9c113e65f53f7ca40e5925.png" />
</div>

<hr/>

Spotify clone app that displays user playlists and songs. It has music player functionality using Spotify APIs.

The project was developed with the principles of Clean Architecture that is based on 3 layers: data, domain, presentation.

The clean architecture was defined according to the image below created by Robert C. Martin:

<img width="500" title="Clean Arch" src="https://diogostein.dev/assets/clean-arch-06a79607b3534a7f9ae666bc081f63b456dcc2de65c9792eb3546d905c53a000.jpg" />

<hr/>

<h3>Architecture &amp; Technologies</h3>
<ul>
  <li>Clean architecture with MVVM pattern</li>
  <li>Data layer using repository + data sources</li>
  <li>Coroutines for asynchronous programming and managing long tasks</li>
  <li>HTTP client and API connection configuration with Retrofit</li>
  <li>Dependency Injection with Dagger Hilt</li>
  <li>Loading and caching images with Glide</li>
  <li>Basic unit tests with JUnit4 and Mockito</li>
</ul>
