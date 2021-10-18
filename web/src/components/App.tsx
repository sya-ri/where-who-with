import React, { FC, lazy, Suspense } from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import * as Paths from '../Paths';
import { AlertProvider } from '../context/AlertContext';
const Index = lazy(() => import('./pages/IndexPage'));
const Area = lazy(() => import('./routes/AreaRoute'));
const AreaDocs = lazy(() => import('./routes/AreaDocsRoute'));
const Desk = lazy(() => import('./routes/DeskRoute'));
const DeskView = lazy(() => import('./routes/DeskViewRoute'));
const DeskDocs = lazy(() => import('./routes/DeskDocsRoute'));
const NotFound = lazy(() => import('./pages/NotFoundPage'));
const User = lazy(() => import('./routes/UserRoute'));

const App: FC = () => (
  <AlertProvider>
    <div className="h-full">
      <Suspense fallback={<></>}>
        <BrowserRouter>
          <Switch>
            <Route exact path={Paths.Index} component={Index} />
            <Route exact path={Paths.Area(':uuid')} component={Area} />
            <Route exact path={Paths.AreaDocs(':uuid')} component={AreaDocs} />
            <Route exact path={Paths.Desk(':uuid')} component={Desk} />
            <Route exact path={Paths.DeskDocs(':uuid')} component={DeskDocs} />
            <Route exact path={Paths.DeskView(':uuid')} component={DeskView} />
            <Route exact path={Paths.User(':uuid')} component={User} />
            <Route exact component={NotFound} />
          </Switch>
        </BrowserRouter>
      </Suspense>
    </div>
  </AlertProvider>
);

export default App;
