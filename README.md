[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

# play-vue-seed
Scala starter app with Play, Vue CLI 3

This is a multi-page web application that combines [Play starter app](https://www.playframework.com/documentation/2.6.x/NewApplication) and [vue-cli-3](https://cli.vuejs.org/guide/creating-a-project.html#installation) based front-end app.

The Vue app is placed under `/frontend` folder and the prod build places the front-end artifacts under /public folder, which is picked up by Play app to serve the artifacts.

## Get Started

Clone the Git Repo & open the terminal in the root dir of the app.

> sbt run

Play [RunHooks](https://www.playframework.com/documentation/2.6.x/SBTCookbook) are in place to run various npm commands required to startup the frontend Dev server as part of `sbt run`. 

The hook generates and stores locally a hash of `package.json` to detect changes to it and runs `npm install` when required.

Additionally, the hook invokes `npm serve` to spin up a webpack dev server to serve front-end artifacts over port 8080.

The play app is started up on port 9000.

## Production Build

Notice that the Vue app is missing the traditional `webpack.config.js`. And instead we see a `vue.config.js`.  Vue CLI 3 abstracts out the webpack config and comes with a bare-bones webpack setup out of the box.

This does not mean though that we cannot add to the webpack config. It can be done by providing webpack config as part of `configureWebpack` object in `vue.config.js`. 

In this app template, we keep all the additional webpack config in a separate file called `vue.webpack.config.js` and import it in `vue.config.js`.

To invoke the prod build, simply issue below sbt command:
> sbt dist

Utilizing the sbt config as setup in `npm-build.sbt`, this resolves to same as below,
> npm install

> npm build

The production build creates the html, js, css and all other front-end artifacts and places it in `/public` folder.

## Multi-Page 
Multiple pages are configured using the [pages](https://cli.vuejs.org/config/#pages) configuration provided by Vue CLI. 
See `vue.config.js` for inspecting config in this project.

This app is configured with 2 pages and can be found in below folder structure:
```
src/
    /pages
      /app
        <!-- JS, Vue & CSS files -->
      /todo
        <!-- JS, Vue & CSS files -->
``` 
1) app -> The default Vue app that comes with a vue-cli starter project
2) todo -> A todo app taken from [Vue examples](http://todomvc.com/examples/vue/) and converted into an SFC Vue app)

Upon invoking a production build, 2 separate html files get created in `/public` folder (`index.html`, `todo.html`) and can be readily served by Play Controllers.

## Templating Engine?

I have retained a Twirl served page in this app, exposed under `/playApp` route in the app context. However, the intent of this seed app is to provide a template for setting up a completely decoupled web stack wherein Play is used simply to serve an API to front-end.
 
 ## License
[Apache License 2.0](https://github.com/angadsalaria/play-vue-seed/blob/master/LICENSE)
