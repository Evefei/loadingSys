import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './vehicle.reducer';
import { IVehicle } from 'app/shared/model/vehicle.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IVehicleDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class VehicleDetail extends React.Component<IVehicleDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { vehicleEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="loadingSysApp.vehicle.detail.title">Vehicle</Translate> [<b>{vehicleEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="carLicenseNo">
                <Translate contentKey="loadingSysApp.vehicle.carLicenseNo">Car License No</Translate>
              </span>
            </dt>
            <dd>{vehicleEntity.carLicenseNo}</dd>
            <dt>
              <span id="length">
                <Translate contentKey="loadingSysApp.vehicle.length">Length</Translate>
              </span>
            </dt>
            <dd>{vehicleEntity.length}</dd>
            <dt>
              <span id="width">
                <Translate contentKey="loadingSysApp.vehicle.width">Width</Translate>
              </span>
            </dt>
            <dd>{vehicleEntity.width}</dd>
            <dt>
              <span id="height">
                <Translate contentKey="loadingSysApp.vehicle.height">Height</Translate>
              </span>
            </dt>
            <dd>{vehicleEntity.height}</dd>
            <dt>
              <span id="vehicleVolume">
                <Translate contentKey="loadingSysApp.vehicle.vehicleVolume">Vehicle Volume</Translate>
              </span>
            </dt>
            <dd>{vehicleEntity.vehicleVolume}</dd>
            <dt>
              <span id="vehicleWeight">
                <Translate contentKey="loadingSysApp.vehicle.vehicleWeight">Vehicle Weight</Translate>
              </span>
            </dt>
            <dd>{vehicleEntity.vehicleWeight}</dd>
            <dt>
              <span id="underpanHeight">
                <Translate contentKey="loadingSysApp.vehicle.underpanHeight">Underpan Height</Translate>
              </span>
            </dt>
            <dd>{vehicleEntity.underpanHeight}</dd>
            <dt>
              <span id="crankHeight">
                <Translate contentKey="loadingSysApp.vehicle.crankHeight">Crank Height</Translate>
              </span>
            </dt>
            <dd>{vehicleEntity.crankHeight}</dd>
            <dt>
              <span id="crankWidth">
                <Translate contentKey="loadingSysApp.vehicle.crankWidth">Crank Width</Translate>
              </span>
            </dt>
            <dd>{vehicleEntity.crankWidth}</dd>
          </dl>
          <Button tag={Link} to="/entity/vehicle" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/vehicle/${vehicleEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ vehicle }: IRootState) => ({
  vehicleEntity: vehicle.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(VehicleDetail);
