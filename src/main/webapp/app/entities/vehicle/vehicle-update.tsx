import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './vehicle.reducer';
import { IVehicle } from 'app/shared/model/vehicle.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IVehicleUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IVehicleUpdateState {
  isNew: boolean;
}

export class VehicleUpdate extends React.Component<IVehicleUpdateProps, IVehicleUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { vehicleEntity } = this.props;
      const entity = {
        ...vehicleEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/vehicle');
  };

  render() {
    const { vehicleEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="loadingSysApp.vehicle.home.createOrEditLabel">
              <Translate contentKey="loadingSysApp.vehicle.home.createOrEditLabel">Create or edit a Vehicle</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : vehicleEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="vehicle-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="carLicenseNoLabel" for="carLicenseNo">
                    <Translate contentKey="loadingSysApp.vehicle.carLicenseNo">Car License No</Translate>
                  </Label>
                  <AvField id="vehicle-carLicenseNo" type="text" name="carLicenseNo" />
                </AvGroup>
                <AvGroup>
                  <Label id="lengthLabel" for="length">
                    <Translate contentKey="loadingSysApp.vehicle.length">Length</Translate>
                  </Label>
                  <AvField id="vehicle-length" type="text" name="length" />
                </AvGroup>
                <AvGroup>
                  <Label id="widthLabel" for="width">
                    <Translate contentKey="loadingSysApp.vehicle.width">Width</Translate>
                  </Label>
                  <AvField id="vehicle-width" type="text" name="width" />
                </AvGroup>
                <AvGroup>
                  <Label id="heightLabel" for="height">
                    <Translate contentKey="loadingSysApp.vehicle.height">Height</Translate>
                  </Label>
                  <AvField id="vehicle-height" type="text" name="height" />
                </AvGroup>
                <AvGroup>
                  <Label id="vehicleVolumeLabel" for="vehicleVolume">
                    <Translate contentKey="loadingSysApp.vehicle.vehicleVolume">Vehicle Volume</Translate>
                  </Label>
                  <AvField id="vehicle-vehicleVolume" type="text" name="vehicleVolume" />
                </AvGroup>
                <AvGroup>
                  <Label id="vehicleWeightLabel" for="vehicleWeight">
                    <Translate contentKey="loadingSysApp.vehicle.vehicleWeight">Vehicle Weight</Translate>
                  </Label>
                  <AvField id="vehicle-vehicleWeight" type="text" name="vehicleWeight" />
                </AvGroup>
                <AvGroup>
                  <Label id="underpanHeightLabel" for="underpanHeight">
                    <Translate contentKey="loadingSysApp.vehicle.underpanHeight">Underpan Height</Translate>
                  </Label>
                  <AvField id="vehicle-underpanHeight" type="text" name="underpanHeight" />
                </AvGroup>
                <AvGroup>
                  <Label id="crankHeightLabel" for="crankHeight">
                    <Translate contentKey="loadingSysApp.vehicle.crankHeight">Crank Height</Translate>
                  </Label>
                  <AvField id="vehicle-crankHeight" type="text" name="crankHeight" />
                </AvGroup>
                <AvGroup>
                  <Label id="crankWidthLabel" for="crankWidth">
                    <Translate contentKey="loadingSysApp.vehicle.crankWidth">Crank Width</Translate>
                  </Label>
                  <AvField id="vehicle-crankWidth" type="text" name="crankWidth" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/vehicle" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  vehicleEntity: storeState.vehicle.entity,
  loading: storeState.vehicle.loading,
  updating: storeState.vehicle.updating,
  updateSuccess: storeState.vehicle.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(VehicleUpdate);
