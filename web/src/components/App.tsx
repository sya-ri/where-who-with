import React, { FC } from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import Area from './pages/Area';
import Desk from './pages/Desk';
import * as Pages from '../Pages';
import DeskView from './pages/DeskView';
import User from './pages/User';

const App: FC = () => (
  <div className="app">
    <BrowserRouter>
      <div>
        <Switch>
          <Route exact path={Pages.Area(':uuid')} component={Area} />
          <Route exact path={Pages.Desk(':uuid')} component={Desk} />
          <Route exact path={Pages.DeskView(':uuid')} component={DeskView} />
          <Route exact path={Pages.User(':uuid')} component={User} />
        </Switch>
      </div>
    </BrowserRouter>
  </div>
);

export default App;
