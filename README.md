# Youtube-Wrapped
A project designed to emulate features similar to Spotify Wrapped, but for YouTube. The project aims to give users a deep dive into their YouTube watching habits, offering detailed insights, trends, and statistics based on their watch history.

_NOTE: This is an ongoing project. Data enrichment service to be added shortly._

## Prerequisites

### Google Takeout

1. Visit [Google Takeout](https://takeout.google.com/settings/takeout).
2. Under "Select data to include", tick "YouTube and YouTube Music".
3. Change "All YouTube data included" to only include "history".
4. Click on "Multiple formats", and scroll down and switch `HTML` to `JSON`.
5. Click "Next step" and "Create export".
6. Download the generated archive when it's ready.
7. Extract the `watch-history.json` file from the archive. If you have downloaded your Takeout data in any other language, rename the corresponding file to **watch-history.json**.

## Usage

1. Place the `watch-history.json` file at the root of this project.
2. Run.
