import React, { FC } from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import * as Pages from '../Pages';
import Area from './pages/Area';
import Desk from './pages/Desk';
import DeskView from './pages/DeskView';
import NotFound from './pages/NotFound';
import User from './pages/User';

const App: FC = () => (
  <div className="app h-full">
    <div className="h-full flex justify-center items-center">
      <BrowserRouter>
        <Switch>
          <Route exact path={Pages.Area(':uuid')} component={Area} />
          <Route exact path={Pages.Desk(':uuid')} component={Desk} />
          <Route exact path={Pages.DeskView(':uuid')} component={DeskView} />
          <Route exact path={Pages.User(':uuid')} component={User} />
          <Route exact component={NotFound} />
        </Switch>
      </BrowserRouter>
    </div>
  </div>
);

export default App;
