import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './loading.reducer';
import { ILoading } from 'app/shared/model/loading.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ILoadingUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ILoadingUpdateState {
  isNew: boolean;
}

export class LoadingUpdate extends React.Component<ILoadingUpdateProps, ILoadingUpdateState> {
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
      const { loadingEntity } = this.props;
      const entity = {
        ...loadingEntity,
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
    this.props.history.push('/entity/loading');
  };

  render() {
    const { loadingEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="loadingSysApp.loading.home.createOrEditLabel">
              <Translate contentKey="loadingSysApp.loading.home.createOrEditLabel">Create or edit a Loading</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : loadingEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="loading-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="loadingNoLabel" for="loadingNo">
                    <Translate contentKey="loadingSysApp.loading.loadingNo">Loading No</Translate>
                  </Label>
                  <AvField id="loading-loadingNo" type="text" name="loadingNo" />
                </AvGroup>
                <AvGroup>
                  <Label id="volumeUtilizationLabel" for="volumeUtilization">
                    <Translate contentKey="loadingSysApp.loading.volumeUtilization">Volume Utilization</Translate>
                  </Label>
                  <AvField id="loading-volumeUtilization" type="string" className="form-control" name="volumeUtilization" />
                </AvGroup>
                <AvGroup>
                  <Label id="weightUtilizationLabel" for="weightUtilization">
                    <Translate contentKey="loadingSysApp.loading.weightUtilization">Weight Utilization</Translate>
                  </Label>
                  <AvField id="loading-weightUtilization" type="string" className="form-control" name="weightUtilization" />
                </AvGroup>
                <AvGroup>
                  <Label id="materialQuantityLabel" for="materialQuantity">
                    <Translate contentKey="loadingSysApp.loading.materialQuantity">Material Quantity</Translate>
                  </Label>
                  <AvField id="loading-materialQuantity" type="string" className="form-control" name="materialQuantity" />
                </AvGroup>
                <AvGroup>
                  <Label id="materialVolumeLabel" for="materialVolume">
                    <Translate contentKey="loadingSysApp.loading.materialVolume">Material Volume</Translate>
                  </Label>
                  <AvField id="loading-materialVolume" type="text" name="materialVolume" />
                </AvGroup>
                <AvGroup>
                  <Label id="materialWeightLabel" for="materialWeight">
                    <Translate contentKey="loadingSysApp.loading.materialWeight">Material Weight</Translate>
                  </Label>
                  <AvField id="loading-materialWeight" type="text" name="materialWeight" />
                </AvGroup>
                <AvGroup>
                  <Label id="lengthRemainLabel" for="lengthRemain">
                    <Translate contentKey="loadingSysApp.loading.lengthRemain">Length Remain</Translate>
                  </Label>
                  <AvField id="loading-lengthRemain" type="text" name="lengthRemain" />
                </AvGroup>
                <AvGroup>
                  <Label id="widthRemainLabel" for="widthRemain">
                    <Translate contentKey="loadingSysApp.loading.widthRemain">Width Remain</Translate>
                  </Label>
                  <AvField id="loading-widthRemain" type="text" name="widthRemain" />
                </AvGroup>
                <AvGroup>
                  <Label id="heightRemainLabel" for="heightRemain">
                    <Translate contentKey="loadingSysApp.loading.heightRemain">Height Remain</Translate>
                  </Label>
                  <AvField id="loading-heightRemain" type="text" name="heightRemain" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/loading" replace color="info">
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
  loadingEntity: storeState.loading.entity,
  loading: storeState.loading.loading,
  updating: storeState.loading.updating,
  updateSuccess: storeState.loading.updateSuccess
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
)(LoadingUpdate);
