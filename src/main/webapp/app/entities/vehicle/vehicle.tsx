import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './vehicle.reducer';
import { IVehicle } from 'app/shared/model/vehicle.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IVehicleProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Vehicle extends React.Component<IVehicleProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { vehicleList, match } = this.props;
    return (
      <div>
        <h2 id="vehicle-heading">
          <Translate contentKey="loadingSysApp.vehicle.home.title">Vehicles</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="loadingSysApp.vehicle.home.createLabel">Create new Vehicle</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="loadingSysApp.vehicle.carLicenseNo">Car License No</Translate>
                </th>
                <th>
                  <Translate contentKey="loadingSysApp.vehicle.length">Length</Translate>
                </th>
                <th>
                  <Translate contentKey="loadingSysApp.vehicle.width">Width</Translate>
                </th>
                <th>
                  <Translate contentKey="loadingSysApp.vehicle.height">Height</Translate>
                </th>
                <th>
                  <Translate contentKey="loadingSysApp.vehicle.vehicleVolume">Vehicle Volume</Translate>
                </th>
                <th>
                  <Translate contentKey="loadingSysApp.vehicle.vehicleWeight">Vehicle Weight</Translate>
                </th>
                <th>
                  <Translate contentKey="loadingSysApp.vehicle.underpanHeight">Underpan Height</Translate>
                </th>
                <th>
                  <Translate contentKey="loadingSysApp.vehicle.crankHeight">Crank Height</Translate>
                </th>
                <th>
                  <Translate contentKey="loadingSysApp.vehicle.crankWidth">Crank Width</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {vehicleList.map((vehicle, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${vehicle.id}`} color="link" size="sm">
                      {vehicle.id}
                    </Button>
                  </td>
                  <td>{vehicle.carLicenseNo}</td>
                  <td>{vehicle.length}</td>
                  <td>{vehicle.width}</td>
                  <td>{vehicle.height}</td>
                  <td>{vehicle.vehicleVolume}</td>
                  <td>{vehicle.vehicleWeight}</td>
                  <td>{vehicle.underpanHeight}</td>
                  <td>{vehicle.crankHeight}</td>
                  <td>{vehicle.crankWidth}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${vehicle.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${vehicle.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${vehicle.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ vehicle }: IRootState) => ({
  vehicleList: vehicle.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Vehicle);
