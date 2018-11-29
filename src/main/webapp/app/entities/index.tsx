import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Loading from './loading';
import LoadingInfo from './loading-info';
import Vehicle from './vehicle';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/loading`} component={Loading} />
      <ErrorBoundaryRoute path={`${match.url}/loading-info`} component={LoadingInfo} />
      <ErrorBoundaryRoute path={`${match.url}/vehicle`} component={Vehicle} />
      {/* jhipster-needle-add-route-path - JHipster will routes here */}
    </Switch>
  </div>
);

export default Routes;
