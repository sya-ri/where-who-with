import React, { FC, lazy, Suspense } from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import * as Pages from '../Pages';
const Area = lazy(() => import('./pages/Area'));
const Desk = lazy(() => import('./pages/Desk'));
const DeskView = lazy(() => import('./pages/DeskView'));
const NotFound = lazy(() => import('./pages/NotFound'));
const User = lazy(() => import('./pages/User'));

const App: FC = () => (
  <div className="app h-full">
    <div className="h-full flex justify-center items-center">
      <Suspense fallback={<></>}>
        <BrowserRouter>
          <Switch>
            <Route exact path={Pages.Area(':uuid')} component={Area} />
            <Route exact path={Pages.Desk(':uuid')} component={Desk} />
            <Route exact path={Pages.DeskView(':uuid')} component={DeskView} />
            <Route exact path={Pages.User(':uuid')} component={User} />
            <Route exact component={NotFound} />
          </Switch>
        </BrowserRouter>
      </Suspense>
    </div>
  </div>
);

export default App;
